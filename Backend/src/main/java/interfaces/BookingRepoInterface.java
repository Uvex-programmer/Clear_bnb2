package interfaces;
import models.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepoInterface {

    Optional<Booking> findById(Integer id);

    List<?> findAll();

    Optional<Booking> save(Booking booking);
}
