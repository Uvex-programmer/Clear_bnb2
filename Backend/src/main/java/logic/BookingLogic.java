package logic;

import DTO.BookingDTO;
import DTO.ReviewDTO;
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
    PropertyRepository propertyRepository  = new PropertyRepository();
    UserRepository userRepository  = new UserRepository();
    BookingRepository bookingRepository = new BookingRepository();
    BookingMapper bookingMapper = new BookingMapper();

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
        Optional<Property> property = propertyRepository.findById(propertyId);
        Optional<User> buyer = userRepository.findById(userId);

        Transaction transaction = createTransaction(bookDTO.getPropertyPrice(), buyer.get(), receiver.get());
        Booking booking = bookingMapper.bookingDTOToEntity(bookDTO, property, buyer, transaction);
        bookingRepository.save(booking);
        return Optional.of(booking);
    }

    public String checkCanReviewProperty(Integer num1, Integer num2){
        List<Booking> bookings = bookingRepository.findBookingByPropertyId(num1, num2);
        ArrayList<BookingDTO> books = new ArrayList<>();
        for(Booking b : bookings){
            books.add(bookingMapper.bookingToDTO(Optional.ofNullable(b)));
        }
        if(books.isEmpty())
            return "no";
        return "yes";
    }
    public String checkCanReviewUser(Integer num1, Integer num2){
        var bookings = bookingRepository.findBookingByUser(num1, num2);
        if(bookings.isEmpty()) {
            return "no";
        }
        return "yes";
    }
}
