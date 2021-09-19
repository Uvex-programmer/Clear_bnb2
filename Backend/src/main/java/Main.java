import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import express.Express;
import models.Property;
import models.User;
import repositories.PropertyRepository;
import repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;

public class Main {
    
    public static void main(String[] args) {
        Express app = new Express();
        new ConnectMysql();
        app.listen(4000);
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb_db");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        
        UserRepository userRepository = new UserRepository(entityManager, app, mapper);
        PropertyRepository propertyRepository = new PropertyRepository(entityManager, app);
        
        User user = new User("Pekka", "Päronpung", "pekka@gmail.com", "Rövsmör");
        Property property = new Property("Pekkas hus", "Här bor pekka", 3, 4, 6
                , Date.valueOf("2021-10-10"), Date.valueOf("2022-10-10"), 321);
        user.addProperty(property);
        
        userRepository.save(user);
    }
}
