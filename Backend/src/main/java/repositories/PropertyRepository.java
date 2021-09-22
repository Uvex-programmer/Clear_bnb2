package repositories;

import models.Property;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PropertyRepository implements PropertyRepoInterface {
    
    private final EntityManager entityManager;
    
    public PropertyRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Optional<Property> findById(Integer id) {
        Property property = entityManager.find(Property.class, id);
        return property != null ? Optional.of(property) : Optional.empty();
    }
    
    public List<?> findAvailableObjects() {
        Date currentTime = new Date();
        return entityManager.createQuery("FROM Property P WHERE P.startDate < :currentTime AND P.endDate > :currentTime", Property.class)
                .setParameter("currentTime", currentTime)
                .getResultList();
    }
    
    public List<?> findAll() {
        return entityManager.createQuery("FROM Property P", Property.class)
                .getResultList();
    }
    
    public Optional<Property> findByName(String name) {
        Property property = entityManager.createQuery("FROM Property P WHERE P.title = :name", Property.class)
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