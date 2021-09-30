package routes;

import DTO.BookingDTO;
import express.Express;
import logic.BookingLogic;
import models.Booking;

import java.util.Optional;

public class BookingRoutes {

    private final Express app;
    BookingLogic logic = new BookingLogic();

    public BookingRoutes(Express app) {
        this.app = app;
        this.bookingMethods();
    }


    public void bookingMethods() {

        app.get("/api/getUserBookings/:id", (req, res) -> {
        });

        app.get("/api/get-property-bookings/:id/:id2", (req, res) -> {
            res.json(logic.checkCanReviewProperty(Integer.parseInt(req.params("id")), Integer.parseInt(req.params("id2"))));
        });

        app.get("/api/get-user-bookings/:id/:id2", (req, res) -> {
            res.json(logic.checkCanReviewUser(Integer.parseInt(req.params("id")), Integer.parseInt(req.params("id2"))));
        });

        app.post("/api/purchase-booking", (req, res) -> {
            boolean SuccessfulPayment = logic.transferHandler(req.body(BookingDTO.class), Integer.parseInt(req.body().get("propertyId").toString()), Integer.parseInt(req.body().get("userId").toString()));
            Optional<Booking> booking = logic.createBooking(req.body(BookingDTO.class), Integer.parseInt(req.body().get("propertyId").toString()), Integer.parseInt(req.body().get("userId").toString()));

            if (!SuccessfulPayment) {
                res.send("Tomt på kontot!!");
            } else if (booking.isPresent()) {
                res.json(booking.get()).type("application/json ");
            } else {
                res.send("Date already booked!");
            }
        });
    }

}
