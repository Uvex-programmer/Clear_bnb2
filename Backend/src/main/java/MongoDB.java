import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import models.PropertyView;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import repositories.PropertyRepository;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDB {

    private final PropertyRepository propertyRepository;
    MongoDatabase database;
    MongoCollection<PropertyView> collection;

    public MongoDB(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
        this.connectToMongoDB();
    }

    public void connectToMongoDB() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb+srv://Slobban:1234@cluster0.q0kct.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

        MongoClient mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase("bnb-cache").withCodecRegistry(pojoCodecRegistry);
        collection = database.getCollection("PropertyView", PropertyView.class);

//        List<PropertyView> availableObjects = propertyRepository.findAvailableObjects();
//        collection.insertMany(availableObjects);

        collection.find().forEach(System.out::println);
    }

}
