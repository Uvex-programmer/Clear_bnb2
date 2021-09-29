package util;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import models.PropertyView;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import repositories.PropertyRepository;

import java.util.*;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDB {

    static MongoCollection<PropertyView> collection;
    private final PropertyRepository propertyRepository;
    MongoDatabase database;
    static Set<Integer> idNumbers = new HashSet<>();


    public MongoDB(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
        this.connectToMongoDB();
        this.populateCache();
    }

    public static List<PropertyView> convertToList(FindIterable<PropertyView> mongoCollection) {
        List<PropertyView> list = new ArrayList<>();
        for (PropertyView item : mongoCollection) {
            list.add(item);
        }
        return list;
    }

    public static List<PropertyView> checkIfCached(List<PropertyView> properties) {
        FindIterable<PropertyView> mongoCollection = collection.find();
        List<PropertyView> propertyViews = convertToList(mongoCollection);
    
        if(properties.stream().anyMatch(p -> idNumbers.contains(p.getId())) && idNumbers.size() == properties.size()){
            System.out.println("From mongo");
            return propertyViews;
        }else{
            System.out.println("From SQL");
            return properties;
        }
    }

    private void populateCache() {
        collection.deleteMany(new Document());
        List<PropertyView> availableObjects = propertyRepository.findAvailableObjects();
        
        idNumbers = new HashSet<>();
        availableObjects.forEach(obj -> idNumbers.add(obj.getId()));
        System.out.println(idNumbers);
        
        collection.insertMany(availableObjects);
    }

    public void connectToMongoDB() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        String uri = "mongodb+srv://Slobban:1234@cluster0.q0kct.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

        MongoClient mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase("bnb-cache").withCodecRegistry(pojoCodecRegistry);
        collection = database.getCollection("PropertyView", PropertyView.class);
    }
}
