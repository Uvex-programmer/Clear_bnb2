import express.Express;
import models.Property;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class PropertyRepository {

    private final EntityManager entityManager;
    private Express app;

    public PropertyRepository(EntityManager entityManager, Express app) {
        this.entityManager = entityManager;
        this.app = app;
        this.propertyMethods();
    }

    public void propertyMethods(){
        app.post("/api/addProperty", (req, res) -> {
            Property user = req.body(Property.class);
            save(user);
        });
    }

    public Optional<Property> findById(Integer id) {
        Property property = entityManager.find(Property.class, id);
        return property != null ? Optional.of(property) : Optional.empty();
    }

    public List findAll() {
        return entityManager.createQuery("from Property").getResultList();
    }

    public Optional<Property> findByName(String name) {
        Property property = entityManager.createQuery("SELECT p FROM properties p WHERE p.title = :name", Property.class)
                .setParameter("name", name)
                .getSingleResult();
        return property != null ? Optional.of(property) : Optional.empty();
    }

    public Optional<Property> save(Property property) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(property);
            entityManager.getTransaction().commit();
            return Optional.of(property);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}