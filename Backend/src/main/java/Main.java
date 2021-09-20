import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import express.Express;
import models.*;
import repositories.*;
import routes.UserRoutes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

public class Main {
    
    public static void main(String[] args) {
        Express app = new Express();
        new ConnectMysql();
        app.listen(4000);
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        
        UserRepository userRepository = new UserRepository(entityManager);
        PropertyRepository propertyRepository = new PropertyRepository(entityManager, app);
        BankAccountRepository bankRepository = new BankAccountRepository(entityManager, app, mapper);
        BookingRepository bookingRepository = new BookingRepository(entityManager, app, mapper);
        TransactionRepository transResp = new TransactionRepository(entityManager, app, mapper);
        AmenityRepository amenRep = new AmenityRepository(entityManager);
        ReviewRepository revRep = new ReviewRepository(entityManager, app, mapper);
        new UserRoutes(app, mapper, userRepository);


//
        Image image = new Image("Stens_hus.jpg", true);
        Image image2 = new Image("Vuxenbild.jpg", false);
        Optional<User> user = userRepository.findById(75);
        Optional<User> jons = userRepository.findById(66);
        BankAccount account = bankRepository.findById(62);
        Optional<Property> stensHus = propertyRepository.findById(72);
        Booking stensBooking = bookingRepository.findById(1);
        Transaction stenTrans = transResp.findById(5);
        Review review = revRep.findById(201);
        
        System.out.println(user.get().getProperties());
    }
}
