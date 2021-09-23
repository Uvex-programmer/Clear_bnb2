import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import express.Express;
import models.*;
import repositories.*;
import routes.PropertyRoutes;
import routes.UserRoutes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;

public class Main {
    
    public static void main(String[] args) {
        Express app = new Express();
        new ConnectMysql();

        app.use((req, res) -> {
            //System.out.println("HÄR KOMMER MIN SESSION: ");
            //Session ska vara i 15-30 minuter
            // Set an cookie (you can call setCookie how often you want)
            //res.setCookie(new Cookie("my-cookie", "Hello World!"));
        });

        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        SessionRepository sessionRepository = new SessionRepository(entityManager);
        UserRepository userRepository = new UserRepository(entityManager);
        PropertyRepository propertyRepository = new PropertyRepository(entityManager);
        BankAccountRepository bankRepository = new BankAccountRepository(entityManager);
        BookingRepository bookingRepository = new BookingRepository(entityManager);
        TransactionRepository transResp = new TransactionRepository(entityManager);
        ReviewRepository revRep = new ReviewRepository(entityManager);

        new UserRoutes(app, mapper, userRepository, sessionRepository);

        Optional<User> user = userRepository.findById(75);
        Optional<User> jons = userRepository.findById(66);
        Optional<BankAccount> account = bankRepository.findById(62);
        Optional<Property> stensHus = propertyRepository.findById(72);
        Optional<Booking> stensBooking = bookingRepository.findById(1);
        Optional<Transaction> stenTrans = transResp.findById(5);
        Optional<Review> review = revRep.findById(201);
        
        List<?> list = bankRepository.findAll();
        System.out.println(list);
        new PropertyRoutes(app, mapper, propertyRepository);
        
        app.listen(4000);
    }
}
