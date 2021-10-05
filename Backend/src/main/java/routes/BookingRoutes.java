package routes;

import DTO.BookingDTO;
import express.Express;
import logic.BookingLogic;
import models.Booking;

import java.util.HashMap;
import java.util.Map;
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

        app.post("/api/purchase-booking", (req, res) -> {
            boolean gotCoverage = logic.gotCoverage(req.body(BookingDTO.class), Integer.parseInt(req.body().get("userId").toString()));
            
            if(gotCoverage){
            Optional<Booking> booking = logic.createBooking(req.body(BookingDTO.class), Integer.parseInt(req.body().get("propertyId").toString()), Integer.parseInt(req.body().get("userId").toString()));
                if(booking.isPresent()){
                    logic.transferHandler(req.body(BookingDTO.class), Integer.parseInt(req.body().get("propertyId").toString()), Integer.parseInt(req.body().get("userId").toString()));
                    res.json(booking.get()).type("application/json ");
                }else{
                    res.json("Date already booked!");
                }
            }else{
                res.json("Tomt på kontot!!");
            }
        });
    }

}
