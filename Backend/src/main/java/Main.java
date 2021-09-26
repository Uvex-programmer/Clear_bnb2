import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import express.Express;
import repositories.AmenityRepository;
import repositories.PropertyRepository;
import repositories.SessionRepository;
import repositories.UserRepository;
import models.*;
import repositories.*;
import routes.BookingRoutes;
import routes.PropertyRoutes;
import routes.ReviewRoutes;
import routes.UserRoutes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    
    public static void main(String[] args) {
        Express app = new Express();
        //new ConnectMysql();

//        app.use((req, res) -> {
//            //System.out.println("HÄR KOMMER MIN SESSION: ");
//            //Session ska vara i 15-30 minuter
//            // Set an cookie (you can call setCookie how often you want)
//            //res.setCookie(new Cookie("my-cookie", "Hello World!"));
//        });

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        SessionRepository sessionRepository = new SessionRepository();
        UserRepository userRepository = new UserRepository();
        PropertyRepository propertyRepository = new PropertyRepository();
        BookingRepository bookingRepository = new BookingRepository();
        ReviewRepository reviewRepository = new ReviewRepository();

        AmenityRepository amenityRepository = new AmenityRepository();

        new UserRoutes(app, mapper, userRepository, sessionRepository);
        new PropertyRoutes(app, mapper, propertyRepository);
        new BookingRoutes(app, mapper, bookingRepository);
        new ReviewRoutes(app, mapper, reviewRepository);


        app.listen(4000);
    }
}
