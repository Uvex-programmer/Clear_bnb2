package repositories;

import interfaces.PropertyRepoInterface;
import models.Property;
import models.PropertyView;
import models.User;
import org.hibernate.Filter;
import org.hibernate.Session;
import util.MongoDB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PropertyRepository implements PropertyRepoInterface {
    
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    public PropertyRepository() {
    
    }
    
    public Optional<Property> findById(Integer id) {
        Property property = entityManager.find(Property.class, id);
        return property != null ? Optional.of(property) : Optional.empty();
    }
    
    public List<PropertyView> findAll() {
        return entityManager.createQuery("SELECT v FROM PropertyView v", PropertyView.class).getResultList();
    }
    
    public Optional<User> findByIdReturnUserId(Integer id) {
        User user = entityManager.createNamedQuery("Property.findByPropertyIdReturnUser", User.class)
                .setParameter("id", id)
                .getSingleResult();
        return user != null ? Optional.of(user) : Optional.empty();
    }
    
    
    public List<Property> findAvailableObjects() {
        Date currentTime = new Date();
        return entityManager.createQuery("SELECT P FROM Property P WHERE P.startDate <= :currentTime AND P.endDate > :currentTime", Property.class)
                .setParameter("currentTime", currentTime)
                .getResultList();
    }

    public List<PropertyView> findObjectsBySearch(String freeSearch, int beds, int bathrooms, int minGuests,
                                                  int maxPrice, java.sql.Timestamp startDate, java.sql.Timestamp endDate) {
        List<String> filters = List.of("bedroomfilter", "bathroomFilter", "guestFilter", "priceFilter", "dateFilter", "cityFilter");
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("bedroomFilter").setParameter("minBeds", beds);
        session.enableFilter("bathroomFilter").setParameter("minBath", bathrooms);
        session.enableFilter("guestFilter").setParameter("minGuests", minGuests);
        session.enableFilter("priceFilter").setParameter("maxPrice", maxPrice);
        Filter dateFilter = session.enableFilter("dateFilter").setParameter("startDate", startDate);
        dateFilter.setParameter("endDate", endDate);
        session.enableFilter("cityFilter").setParameter("city", freeSearch);
        List<PropertyView> views = entityManager.createQuery("SELECT v FROM PropertyView v", PropertyView.class).getResultList();
        filters.forEach(session::disableFilter);
        return views;
    }
    
    public Optional<Property> findByName(String name) {
        Property property = entityManager.createQuery("FROM Property P WHERE P.title = :name", Property.class)
                .setParameter("name", name)
                .getSingleResult();
        return property != null ? Optional.of(property) : Optional.empty();
    }
    
    public List<Property> findByUserId(Integer id) {
        return entityManager.createQuery("FROM Property p Where p.user.id = :id", Property.class)
                .setParameter("id", id)
                .getResultList();
    }
    
    public void updateProperty(Property p) {
        try {
            MongoDB.updateProperty(p);
            entityManager.getTransaction().begin();
            entityManager.createQuery("DELETE FROM Address a WHERE a.property = :id")
                    .setParameter("id", p)
                    .executeUpdate();
            entityManager.createQuery("DELETE FROM Image i WHERE i.property = :id")
                    .setParameter("id", p)
                    .executeUpdate();
            entityManager.merge(p);
            entityManager.getTransaction().commit();
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
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