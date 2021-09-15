import models.Property;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class PropertyRepository {
    private final EntityManager entityManager;
    public PropertyRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public Optional<Property> findById(Integer id) {
        Property property = entityManager.find(Property.class, id);
        return property != null ? Optional.of(property) : Optional.empty();
    }
    public List findAll() {
        return entityManager.createQuery("from Property").getResultList();
    }
    public Optional<Property> findByName(String name) {
        Property property = entityManager.createQuery("SELECT b FROM Book b WHERE b.name = :name", Property.class)
                .setParameter("name", name)
                .getSingleResult();
        return property != null ? Optional.of(property) : Optional.empty();
    }
    public Optional<Property> findByNameNamedQuery(String name) {
        Property property = entityManager.createNamedQuery("Book.findByName", Property.class)
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