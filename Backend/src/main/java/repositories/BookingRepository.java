package repositories;

import models.Booking;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BookingRepository {
    
    private final EntityManager entityManager;
    
    public BookingRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Booking findById(Integer id) {
        return entityManager.find(Booking.class, id);
    }
    
    public List<?> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }
    
    public Optional<Booking> save(Booking booking) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(booking);
            entityManager.getTransaction().commit();
            return Optional.of(booking);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
