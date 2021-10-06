package mapper;

import DTO.BookingDTO;
import models.Booking;
import models.Property;
import models.Transaction;
import models.User;

import java.util.Optional;

public class BookingMapper {

    public Booking bookingDTOToEntity(BookingDTO b, Optional<Property> property, Optional<User> user, Transaction transaction) {
        Booking booking = new Booking();
        booking.setProperty(property.get());
        booking.setStartDate(b.getStartDate());
        booking.setEndDate(b.getEndDate());
        booking.setUser(user.get());
        booking.setTransaction(transaction);
        return booking;
    }
}
