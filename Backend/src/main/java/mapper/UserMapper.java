package mapper;
import DTO.UserDTO;
import models.User;

import java.util.Optional;

public class UserMapper {

    public UserDTO userToDTO(Optional<User> user) {
        return new UserDTO(user.get().getId(), user.get().getFirstName(), user.get().getLastName(),
                user.get().getEmail());
    }
}
