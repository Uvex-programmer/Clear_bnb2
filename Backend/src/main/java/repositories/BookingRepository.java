package repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import models.Booking;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BookingRepository {
    
    private final EntityManager entityManager;
    private final Express app;
    private final ObjectMapper mapper;
    
    public BookingRepository(EntityManager entityManager, Express app, ObjectMapper mapper) {
        this.entityManager = entityManager;
        this.app = app;
        this.mapper = mapper;
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
