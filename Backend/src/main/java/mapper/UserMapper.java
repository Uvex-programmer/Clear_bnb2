package mapper;
import DTO.UserDTO;
import models.User;
import java.util.Optional;

public class UserMapper {

    public UserDTO userToDTO(Optional<User> user) {
        var u = user.get();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(u.getId());
        userDTO.setEmail(u.getEmail());
        userDTO.setFirstName(u.getFirstName());
        userDTO.setLastName(u.getLastName());
        return userDTO;
    }
}
