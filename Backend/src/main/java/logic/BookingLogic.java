package logic;

import DTO.BookingDTO;
import mapper.BookingMapper;
import models.Booking;
import models.Property;
import models.Transaction;
import models.User;
import repositories.BookingRepository;
import repositories.PropertyRepository;
import repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingLogic {
    PropertyRepository propertyRepository = new PropertyRepository();
    UserRepository userRepository = new UserRepository();
    BookingRepository bookingRepository = new BookingRepository();
    BookingMapper bookingMapper = new BookingMapper();

    public Transaction createTransaction(int price, User giver, User receiver) {
        return new Transaction(price, giver, receiver);
    }

    public Booking createBooking(BookingDTO bookDTO, int propertyId, int userId) {

        Optional<User> receiver = propertyRepository.findByIdReturnUserId(propertyId);
        Optional<Property> property = propertyRepository.findById(propertyId);
        Optional<User> buyer = userRepository.findById(userId);
        Transaction transaction = createTransaction(bookDTO.getPropertyPrice(), buyer.get(), receiver.get());
        Booking booking = bookingMapper.bookingDTOToEntity(bookDTO, property, buyer, transaction);
        bookingRepository.save(booking);
        return booking;

    }

    public boolean transferMoney(User buyer, User receiver, int propertyPrice) {
        double buyerFunds = buyer.getAccount().getFunds();
        double receiverFunds = receiver.getAccount().getFunds();
        if (buyerFunds > propertyPrice) {
            buyer.getAccount().setFunds(buyerFunds - propertyPrice);
            receiver.getAccount().setFunds(receiverFunds + propertyPrice);
            return true;
        }
        return false;
    }

    public String checkCanReviewProperty(Integer num1, Integer num2) {
        List<Booking> bookings = (List<Booking>) bookingRepository.findBookingByPropertyId(num1, num2);
        ArrayList<BookingDTO> books = new ArrayList<>();
        for (Booking b : bookings) {
            books.add(bookingMapper.bookingToDTO(Optional.ofNullable(b)));
        }
        if (books.isEmpty())
            return "no";
        return "yes";
    }

    public String checkCanReviewUser(Integer num1, Integer num2) {
        var bookings = bookingRepository.findBookingByUser(num1, num2);
        if (bookings.isEmpty()) {
            return "no";
        }
        return "yes";
    }
}
