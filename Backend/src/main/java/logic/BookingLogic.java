package logic;

import DTO.BookingDTO;
import DTO.ReviewDTO;
import mapper.BookingMapper;
import models.*;
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

    private Boolean isDateBooked(java.sql.Date start, java.sql.Date end, int propertyId) {
        return bookingRepository.checkIfBooked(start, end, propertyId);
    }

    public Optional<Booking> createBooking(BookingDTO bookDTO, int propertyId, int userId) {
        if (!isDateBooked(bookDTO.getStartDate(), bookDTO.getEndDate(), propertyId)) return Optional.empty();
        Optional<User> receiver = propertyRepository.findByIdReturnUserId(propertyId);
        Optional<Property> property = propertyRepository.findById(propertyId);
        Optional<User> buyer = userRepository.findById(userId);

        Transaction transaction = createTransaction(bookDTO.getPropertyPrice(), buyer.get(), receiver.get());
        Booking booking = bookingMapper.bookingDTOToEntity(bookDTO, property, buyer, transaction);
        bookingRepository.save(booking);
        return Optional.of(booking);
    }
    
    public boolean gotCoverage(BookingDTO bookDTO, int userId){
        double buyerFunds = userRepository.findById(userId).get().getAccount().getFunds();
        int propertyPrice = bookDTO.getPropertyPrice();
        return buyerFunds >= propertyPrice;
    }

    public boolean transferHandler(BookingDTO bookDTO, int propertyId, int userId) {
        Optional<User> buyer = userRepository.findById(userId);
        Optional<User> receiver = propertyRepository.findByIdReturnUserId(propertyId);
        int price = bookDTO.getPropertyPrice();
        return transferMoney(buyer.get(), receiver.get(), price);
    }
//0,8695652173913043
    public boolean transferMoney(User buyer, User receiver, int propertyPrice) {
        double buyerFunds = buyer.getAccount().getFunds();
        double receiverFunds = receiver.getAccount().getFunds();
        Optional<BankAccount> adminAccount = bankAccountRepository.findById(1);
        if (buyerFunds >= propertyPrice) {
            buyer.getAccount().setFunds(buyerFunds - propertyPrice);
            receiver.getAccount().setFunds(receiverFunds + Math.floor(propertyPrice * 0.8695652173913043));
            adminAccount.get().setFunds(adminAccount.get().getFunds() + Math.ceil(propertyPrice * 0.15));
            bankAccountRepository.save(buyer.getAccount());
            bankAccountRepository.save(receiver.getAccount());
            bankAccountRepository.save(adminAccount.get());
            return true;
        }
        return false;
    }

    public String checkCanReviewProperty(Integer num1, Integer num2) {
        List<Booking> bookings = bookingRepository.findBookingByPropertyId(num1, num2);
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
