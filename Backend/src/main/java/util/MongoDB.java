package util;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import models.Property;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import repositories.PropertyRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDB {
    
    static MongoCollection<Property> collection;
    static Set<Integer> idNumbers = new HashSet<>();
    private final PropertyRepository propertyRepository;
    MongoDatabase database;
    
    
    public MongoDB(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
        this.connectToMongoDB();
        populateCache(propertyRepository);
    }
    
    public static List<Property> convertToList(FindIterable<Property> mongoCollection) {
        List<Property> list = new ArrayList<>();
        for (Property item : mongoCollection) {
            list.add(item);
        }
        return list;
    }
    
    public static List<Property> checkIfCached(List<Property> properties) {
        FindIterable<Property> mongoCollection = collection.find();
        List<Property> mongoDbProperties = convertToList(mongoCollection);
        
        if (properties.stream().anyMatch(p -> idNumbers.contains(p.getId())) && idNumbers.size() == properties.size()) {
            System.out.println("From mongo");
            return mongoDbProperties;
        } else {
            System.out.println("From SQL");
            return properties;
        }
    }
    
    public static void populateCache(PropertyRepository propertyRepository) {
        collection.deleteMany(new Document());
        List<Property> availableObjects = propertyRepository.findAvailableObjects();
        
        availableObjects.forEach(obj -> idNumbers.add(obj.getId()));
        
        collection.insertMany(availableObjects);
    }
    
    public static void insertProperty(Property property) {
        idNumbers.add(property.getId());
        collection.insertOne(property);
    }
    
    public void connectToMongoDB() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        String uri = "mongodb+srv://Slobban:1234@cluster0.q0kct.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
        
        MongoClient mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase("bnb-cache").withCodecRegistry(pojoCodecRegistry);
        collection = database.getCollection("Property", Property.class);
    }
}
