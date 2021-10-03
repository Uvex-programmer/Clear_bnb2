import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import express.Express;
import repositories.AmenityRepository;
import repositories.PropertyRepository;
import repositories.SessionRepository;
import repositories.UserRepository;
import models.*;
import repositories.*;
import routes.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import routes.BookingRoutes;
import routes.PropertyRoutes;
import routes.ReviewRoutes;
import routes.UserRoutes;
import util.MongoDB;

public class Main {
    
    public static void main(String[] args) {
        Express app = new Express();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        PropertyRepository propertyRepository = new PropertyRepository();

        new UserRoutes(app);
        new PropertyRoutes(app);
        new BookingRoutes(app);
        new ReviewRoutes(app);
        new AddressRoutes(app);
        new MongoDB(propertyRepository);


        app.listen(4000);
    }
}
