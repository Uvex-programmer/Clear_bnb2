package mapper;

import DTO.BookingDTO;
import models.Booking;
import models.Property;
import models.Transaction;
import models.User;

import java.util.Optional;

public class BookingMapper {

    public BookingDTO bookingToDTO(Optional<Booking> booking) {
        var b = booking.get();
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setPropertyId(b.getProperty().getId());
        bookingDTO.setStartDate(b.getStartDate());
        bookingDTO.setEndDate(b.getEndDate());
        bookingDTO.setUserId(b.getBuyer().getId());
        return bookingDTO;
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
