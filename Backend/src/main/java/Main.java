import models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        new Application();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        UserRepository userRepository = new UserRepository(entityManager);
        var user = userRepository.findByName("else");
       // users.forEach(System.out::println);
        System.out.println(user.toString());

        entityManager.close();
        entityManagerFactory.close();
    }
}
