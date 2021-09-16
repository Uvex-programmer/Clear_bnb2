package repositories;

import express.Express;
import models.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private final EntityManager entityManager;
    private final Express app;

    public UserRepository(EntityManager entityManager, Express app) {
        this.entityManager = entityManager;
        this.app = app;
        this.userMethods();
    }

    public void userMethods(){
        app.post("/api/findUser",(req, res) ->{
            User user = req.body(User.class);
            Optional<User> user1 = Optional.empty();
            try {
            user1 = findByNameAndPassword(user.getFirstName(), user.getPassword());
            }catch (Exception e){
                res.json("wrong login");
                return;
            }
            req.session("current user", "hej");
            res.json(user1.toString());
        });

        app.get("/api/whoami", (req, res)-> {   //Control logged in user
            res.json(req.session("current-user"));
        });
    }

    public Optional<User> findById(Integer id) {
        User user = entityManager.find(User.class, id);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public List findAll() {
        return entityManager.createQuery("from User").getResultList();
    }

    public Optional<User> findByNameAndPassword(String name, String password) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.firstName = :name" +
                " AND u.password = :password", User.class)
                .setParameter("name", name)
                .setParameter("password", password)
                .getSingleResult();
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public Optional<User> save(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
