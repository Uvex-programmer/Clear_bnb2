package interfaces;
import models.User;
import java.util.List;
import java.util.Optional;

public interface UserRepoInterface {

    Optional<User> save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Integer id);

    List<?> findAll();

}
