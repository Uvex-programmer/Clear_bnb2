package mapper;

import DTO.BookingDTO;
import models.Booking;
import models.Property;
import models.Transaction;
import models.User;

import java.util.Optional;

public class BookingMapper {

    public BookingDTO bookingToDTO(Optional<Booking> b) {
        return new BookingDTO(b.get().getProperty().getId(), b.get().getStartDate(), b.get().getEndDate(), b.get().getBuyer().getId());
    }

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
