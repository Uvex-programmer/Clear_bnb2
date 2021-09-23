import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import express.Express;
import repositories.PropertyRepository;
import repositories.UserRepository;
import routes.PropertyRoutes;
import routes.UserRoutes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Timestamp;
import java.util.Date;

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
        
        new UserRoutes(app, mapper, userRepository);
        new PropertyRoutes(app, mapper, propertyRepository);
        
        Timestamp ts1 = new Timestamp(new Date().getTime());
        Timestamp ts2 = new Timestamp(1632573361000L);
        
        System.out.println(propertyRepository.findObjectsBySearch(1, 1, 1, 1000, ts1, ts2));
    }
}
