package logic;

import DTO.BookingDTO;
import models.Booking;
import models.Transaction;
import models.User;
import repositories.BookingRepository;
import repositories.PropertyRepository;
import repositories.UserRepository;

import java.util.Optional;

public class BookingLogic {
    PropertyRepository propertyRepository  = new PropertyRepository();
    UserRepository userRepository  = new UserRepository();
    BookingRepository bookingRepository = new BookingRepository();

    public Transaction createTransaction (int price, User giver, User receiver) {
        Transaction transaction = new Transaction(price, giver, receiver);
        return transaction;
    }

    private Boolean isDateBooked (java.sql.Date start, java.sql.Date end) {
        return bookingRepository.checkIfBooked(start, end);
    }

    public Optional<Booking> createBooking(BookingDTO bookDTO, int propertyId, int userId) {
        if(!isDateBooked(bookDTO.getStartDate(), bookDTO.getEndDate())) return Optional.empty();

        Optional<User> receiver = propertyRepository.findByIdReturnUserId(propertyId);
        Optional<User> giver = userRepository.findById(userId);

        Transaction transaction = createTransaction(bookDTO.getPropertyPrice(), giver.get(), receiver.get());

        Booking booking = new Booking();
        booking.setPropertyId(bookDTO.getPropertyId());
        booking.setStartDate(bookDTO.getStartDate());
        booking.setEndDate(bookDTO.getEndDate());
        booking.setUser(giver.get());
        booking.setTransaction(transaction);

        bookingRepository.save(booking);
        return Optional.of(booking);
    }
}
