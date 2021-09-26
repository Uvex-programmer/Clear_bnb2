package routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import models.Booking;
import models.Property;
import repositories.BookingRepository;
import repositories.PropertyRepository;

import java.util.List;

public class BookingRoutes {

    private final Express app;
    private final BookingRepository bookingRepository;
    private final ObjectMapper mapper;

    public BookingRoutes(Express app, ObjectMapper mapper, BookingRepository bookingRepository) {
        this.app = app;
        this.bookingRepository = bookingRepository;
        this.mapper = mapper;
        this.bookingMethods();
    }

    public void bookingMethods() {

        app.get("/api/getUserBookings/:id", (req, res) -> {
            List<?> bookings = bookingRepository.findByUserId(Integer.parseInt(req.params("id")));
            res.json(mapper.writeValueAsString(bookings));
        });

        app.post("/api/addBooking", (req, res) -> {
            Booking booking = req.body(Booking.class);
            booking.setUser(booking.getUser());
            bookingRepository.save(booking);
        });
    }

}
