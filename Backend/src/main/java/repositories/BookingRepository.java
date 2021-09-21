package repositories;

import models.Booking;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BookingRepository implements BookingRepoInterface {
    
    private final EntityManager entityManager;
    
    public BookingRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Optional<Booking> findById(Integer id) {
        Booking booking = entityManager.find(Booking.class, id);
        return booking != null ? Optional.of(booking) : Optional.empty();
    }
    
    public List<?> findAll() {
        return entityManager.createQuery("from Booking").getResultList();
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
