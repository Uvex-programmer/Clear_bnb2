import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import express.Express;
import org.bson.Document;
import repositories.*;
import routes.BookingRoutes;
import routes.PropertyRoutes;
import routes.ReviewRoutes;
import routes.UserRoutes;

import static com.mongodb.client.model.Filters.eq;

public class Main {
    
    public static void main(String[] args) {
        Express app = new Express();

        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb+srv://Slobban:1234@cluster0.q0kct.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");
            Document doc = collection.find(eq("title", "Back to the Future")).first();
            System.out.println(doc.toJson());
        }


        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        SessionRepository sessionRepository = new SessionRepository();
        UserRepository userRepository = new UserRepository();
        PropertyRepository propertyRepository = new PropertyRepository();
        BookingRepository bookingRepository = new BookingRepository();
        ReviewRepository reviewRepository = new ReviewRepository();


        new UserRoutes(app, mapper, userRepository, sessionRepository);
        new PropertyRoutes(app, mapper, propertyRepository);
        new BookingRoutes(app, mapper, bookingRepository);
        new ReviewRoutes(app, mapper, reviewRepository);


        app.listen(4000);
    }
}
