import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import express.Express;
import repositories.*;
import routes.PropertyRoutes;
import routes.UserRoutes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
        PropertyRepository propertyRepository = new PropertyRepository(entityManager);
        BankAccountRepository bankRepository = new BankAccountRepository(entityManager);
        BookingRepository bookingRepository = new BookingRepository(entityManager);
        TransactionRepository transResp = new TransactionRepository(entityManager);
        AmenityRepository amenRep = new AmenityRepository(entityManager);
        ReviewRepository revRep = new ReviewRepository(entityManager);
        AddressRepository addressRepository = new AddressRepository(entityManager);
        
        new UserRoutes(app, mapper, userRepository);
        new PropertyRoutes(app, mapper, propertyRepository);

//
        System.out.println(propertyRepository.findAvailableObjects());
    }
}
