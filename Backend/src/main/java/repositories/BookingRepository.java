package repositories;

import interfaces.BookingRepoInterface;
import models.Booking;
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

    public Boolean checkIfBooked(java.sql.Date startDate, java.sql.Date endDate, int propertyId) {
        List<Booking> bookings = entityManager.createQuery("FROM Booking p WHERE p.property.id = :id AND :start <= p.endDate AND p.startDate <= :end", Booking.class)
                .setParameter("start", startDate)
                .setParameter("end", endDate)
                .setParameter("id", propertyId)
                .getResultList();
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
    public List<Booking> findBookingByPropertyId(Integer propertyId, Integer userId) {
        return entityManager.createQuery("from Booking b WHERE b.property.id = :id AND b.buyer.id = :id2", Booking.class)
                .setParameter("id", propertyId)
                .setParameter("id2", userId)
                .getResultList();
    }

    public List<?> findBookingByUser(Integer num1, Integer num2) {
        return entityManager.createQuery("FROM Booking b INNER JOIN Property p " +
                        "ON b.property.id = p.id WHERE b.buyer.id = :id AND p.user.id = :id2")
                .setParameter("id", num1)
                .setParameter("id2", num2)
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
