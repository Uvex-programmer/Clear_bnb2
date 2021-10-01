package logic;

import DTO.BookingDTO;
import mapper.BookingMapper;
import models.Booking;
import models.Property;
import models.Transaction;
import models.User;
import repositories.BankAccountRepository;
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
    BankAccountRepository bankAccountRepository = new BankAccountRepository();

    public Transaction createTransaction(int price, User giver, User receiver) {
        return new Transaction(price, giver, receiver);
    }

    private Boolean isDateBooked(java.sql.Date start, java.sql.Date end) {
        return bookingRepository.checkIfBooked(start, end);
    }

    public Optional<Booking> createBooking(BookingDTO bookDTO, int propertyId, int userId) {
        if (!isDateBooked(bookDTO.getStartDate(), bookDTO.getEndDate())) return Optional.empty();

        Optional<User> receiver = propertyRepository.findByIdReturnUserId(propertyId);
        Optional<Property> property = propertyRepository.findById(propertyId);
        Optional<User> buyer = userRepository.findById(userId);
        Transaction transaction = createTransaction(bookDTO.getPropertyPrice(), buyer.get(), receiver.get());
        Booking booking = bookingMapper.bookingDTOToEntity(bookDTO, property, buyer, transaction);
        bookingRepository.save(booking);
        return Optional.of(booking);
    }

    public boolean transferHandler(BookingDTO bookDTO, int propertyId, int userId) {
        Optional<User> buyer = userRepository.findById(userId);
        Optional<User> receiver = propertyRepository.findByIdReturnUserId(propertyId);
        int price = bookDTO.getPropertyPrice();
        return transferMoney(buyer.get(), receiver.get(), price);
    }

    public boolean transferMoney(User buyer, User receiver, int propertyPrice) {
        double buyerFunds = buyer.getAccount().getFunds();
        double receiverFunds = receiver.getAccount().getFunds();
        if (buyerFunds > propertyPrice) {
            buyer.getAccount().setFunds(buyerFunds - propertyPrice);
            receiver.getAccount().setFunds(receiverFunds + propertyPrice);
            bankAccountRepository.save(buyer.getAccount());
            bankAccountRepository.save(receiver.getAccount());
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
