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

    public User userOptionalToUser(Optional<User> user) {
        var u = user.get();
        User user1 = new User();
        user1.setId(u.getId());
        user1.setEmail(u.getEmail());
        user1.setFirstName(u.getFirstName());
        user1.setLastName(u.getLastName());
        user1.setPassword(u.getPassword());
        user1.setCreated_at(u.getCreated_at());
        return user1;
    }
}
