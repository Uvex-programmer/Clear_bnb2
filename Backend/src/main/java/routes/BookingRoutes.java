package routes;

import DTO.BookingDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import logic.BookingLogic;
import models.Booking;
import models.Property;
import models.Transaction;
import models.User;
import repositories.BookingRepository;
import repositories.PropertyRepository;
import repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class BookingRoutes {

    private final Express app;
    private final BookingRepository bookingRepository;
     PropertyRepository propertyRepository  = new PropertyRepository();
    UserRepository userRepository  = new UserRepository();
    private final ObjectMapper mapper;
    BookingLogic logic = new BookingLogic();

    public BookingRoutes(Express app, ObjectMapper mapper, BookingRepository bookingRepository) {
        this.app = app;
        this.bookingRepository = bookingRepository;
        this.mapper = mapper;
        this.bookingMethods();
    }



    public void bookingMethods() {

        app.get("/api/getUserBookings/:id", (req, res) -> {
        });

        app.post("/api/purchase-booking", (req, res) -> {
            res.json(logic.createBooking(req.body(BookingDTO.class), Integer.parseInt(req.body().get("propertyId").toString()), Integer.parseInt(req.body().get("userId").toString())));
        });
    }

}
