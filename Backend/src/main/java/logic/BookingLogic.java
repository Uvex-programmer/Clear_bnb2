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

    public Booking createBooking(BookingDTO bookDTO, int propertyId, int userId) {

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
        return booking;

    }
}
