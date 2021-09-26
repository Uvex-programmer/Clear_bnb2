package repositories;

import models.Session;

import java.util.List;
import java.util.Optional;

public interface SessionRepoInterface {

    Optional<Session> save(Session session);

    Optional<Session> findById(Integer id);

    void deleteById(Session session);

    List<?> findAll();

}
