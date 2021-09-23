package repositories;

import models.Property;
import org.hibernate.Filter;
import org.hibernate.Session;

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
    
    public List<?> findAll() {
        return entityManager.createQuery("from Property").getResultList();
    }
    
    public List<?> findAvailableObjects() {
        Date currentTime = new Date();
        return entityManager.createQuery("FROM Property P WHERE P.startDate < :currentTime AND P.endDate > :currentTime", Property.class)
                .setParameter("currentTime", currentTime)
                .getResultList();
    }
    
    //    , Date startDate, Date endDate
    public List<?> findObjectsBySearch(int beds, int bathrooms, String city, int minGuests, int maxPrice) {
        Session session = entityManager.unwrap(Session.class);
        Filter bedroomFilter = session.enableFilter("bedroomFilter");
        bedroomFilter.setParameter("minBeds", beds);
        Filter bathroomFilter = session.enableFilter("bathroomFilter");
        bathroomFilter.setParameter("minBath", bathrooms);
        Filter cityFilter = session.enableFilter("cityFilter");
        cityFilter.setParameter("city", city);
//        Filter dateFilter = session.enableFilter("dateFilter");
//        dateFilter.setParameter("startDate", startDate);
//        dateFilter.setParameter("endDate", endDate);
        Filter guestFilter = session.enableFilter("guestFilter");
        guestFilter.setParameter("minGuests", minGuests);
        Filter priceFilter = session.enableFilter("priceFilter");
        priceFilter.setParameter("maxPrice", maxPrice);
        return entityManager.createQuery("SELECT P FROM Property P inner join P.address", Property.class)
                .getResultList();
    }
    // date guest price
    
    public Optional<Property> findByName(String name) {
        Property property = entityManager.createQuery("FROM Property P WHERE P.title = :name", Property.class)
                .setParameter("name", name)
                .getSingleResult();
        return property != null ? Optional.of(property) : Optional.empty();
    }
    
    public List<?> findByUserId(Integer id) {
        return entityManager.createNamedQuery("Property.findAllByUserId")
                .setParameter("id", id)
                .getResultList();
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