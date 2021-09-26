package repositories;

import models.Amenity;

import java.util.List;
import java.util.Optional;

public interface AmenityRepoInterface {

    Optional<Amenity> findById(Integer id);

    List<?> findAll();

    Optional<Amenity> save(Amenity amenity);
}
