package repositories;

import models.Image;

import java.util.List;
import java.util.Optional;

public interface ImageRepoInterface {

    Optional<Image> save(Image image);

    Optional<Image> findById(Integer id);

    List<?> findAll();
}
