import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.script.Bindings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import express.Express;
import models.User;
import repositories.PropertyRepository;
import repositories.UserRepository;
import util.PasswordHash;

public class Main {

    public static void main(String[] args) {
        Express app = new Express();
        new ConnectMysql();
        /*app.use("/api/register-user", (req, res) -> {
            final PasswordHash passwordHash = new PasswordHash();
            User user = req.body(User.class);
            String pass = req.body().get("password").toString();
            char[] ch = pass.toCharArray();
            String hashed = passwordHash.hash(ch);
           // req.body().get("password") = hashed;
            System.out.println(hashed);
        });

     */


        app.listen(4000);



        //initializa entity managern = den som interagerar med databasen! initialiseras med peristence.xml filen!
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());


        new UserRepository(entityManager, app, mapper);
        new PropertyRepository(entityManager, app);
    }
}
