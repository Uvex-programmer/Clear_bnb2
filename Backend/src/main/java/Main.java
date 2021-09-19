import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import express.Express;
import models.BankAccount;
import models.User;
import repositories.PropertyRepository;
import repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
        
        User user = new User("Pelle", "Päronpung", "pekka@gmail.com", "Rövsmör");
        BankAccount account = new BankAccount(23000);
        user.addAccount(account);
        
        userRepository.save(user);
    }
}
