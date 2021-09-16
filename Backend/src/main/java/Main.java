import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import express.Express;
import repositories.PropertyRepository;
import repositories.UserRepository;

public class Main {

    public static void main(String[] args) {
        Express app = new Express();
        new ConnectMysql();
        app.listen(4000);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        new UserRepository(entityManager, app, mapper);
        new PropertyRepository(entityManager, app);
    }
}
