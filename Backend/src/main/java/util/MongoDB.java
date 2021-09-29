package util;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import models.PropertyView;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import repositories.PropertyRepository;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDB {

    static MongoCollection<PropertyView> collection;
    private final PropertyRepository propertyRepository;
    MongoDatabase database;


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
        //Ändra datum till timestamps så att man kan jämnföra mongo och sql.
        propertyViews.forEach(System.out::println);
        properties.forEach(System.out::println);
        return propertyViews;

    }

    private void populateCache() {
        collection.deleteMany(new Document());
        collection.insertMany(propertyRepository.findAvailableObjects());
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
