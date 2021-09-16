import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

        new UserRepository(entityManager, app);
        new PropertyRepository(entityManager, app);
    }
}
