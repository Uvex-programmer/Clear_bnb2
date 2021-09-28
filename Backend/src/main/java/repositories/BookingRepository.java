package repositories;

import interfaces.BookingRepoInterface;
import models.Booking;
import models.Property;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class BookingRepository implements BookingRepoInterface {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public BookingRepository() {

    }

    public Boolean checkIfBooked(java.sql.Date startDate, java.sql.Date endDate) {
        System.out.println(startDate);
        System.out.println(endDate);
        List<Booking> bookings = entityManager.createQuery("FROM Booking p WHERE :start <= p.endDate AND p.startDate <= :end", Booking.class)
                .setParameter("start", startDate)
                .setParameter("end", endDate)
                .getResultList();
        System.out.println(bookings);

        return bookings.isEmpty();
    }
    
    public Optional<Booking> findById(Integer id) {
        Booking booking = entityManager.find(Booking.class, id);
        return booking != null ? Optional.of(booking) : Optional.empty();
    }
    
    public List<?> findAll() {
        return entityManager.createQuery("from Booking").getResultList();
    }

    public List<?> findByUserId(Integer id) {
        return entityManager.createNamedQuery("Booking.findAllByUserId")
                .setParameter("id", id)
                .getResultList();
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
