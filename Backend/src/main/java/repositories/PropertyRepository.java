package repositories;

import models.Property;
import models.PropertyView;
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
        return entityManager.createQuery("SELECT v FROM PropertyView v", PropertyView.class).getResultList();
    }
    
    public List<?> findAvailableObjects() {
        Date currentTime = new Date();
        return entityManager.createQuery("FROM Property P WHERE P.startDate < :currentTime AND P.endDate > :currentTime", Property.class)
                .setParameter("currentTime", currentTime)
                .getResultList();
    }
    
    //    , Date startDate, Date endDate
    public List<?> findObjectsBySearch(String city, int beds, int bathrooms, int minGuests, int maxPrice, java.sql.Timestamp startDate, java.sql.Timestamp endDate) {
        Session session = entityManager.unwrap(Session.class);
        Filter bedroomFilter = session.enableFilter("bedroomFilter");
        Filter bathroomFilter = session.enableFilter("bathroomFilter");
        Filter guestFilter = session.enableFilter("guestFilter");
        Filter priceFilter = session.enableFilter("priceFilter");
        Filter dateFilter = session.enableFilter("dateFilter");
        Filter cityFilter = session.enableFilter("cityFilter");
        cityFilter.setParameter("city", city);
        bedroomFilter.setParameter("minBeds", beds);
        bathroomFilter.setParameter("minBath", bathrooms);
        guestFilter.setParameter("minGuests", minGuests);
        priceFilter.setParameter("maxPrice", maxPrice);
        dateFilter.setParameter("startDate", startDate);
        dateFilter.setParameter("endDate", endDate);
        
        return this.findAll();
    }
    
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