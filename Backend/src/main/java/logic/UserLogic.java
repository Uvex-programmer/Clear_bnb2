package logic;

import DTO.UserDTO;
import mapper.UserMapper;
import models.Session;
import models.User;
import repositories.SessionRepository;
import repositories.UserRepository;
import util.PasswordHash;

import javax.servlet.http.Cookie;
import java.sql.Timestamp;
import java.util.Optional;

public class UserLogic {
    
    final PasswordHash passwordHash = new PasswordHash();
    UserRepository userRepository = new UserRepository();
    SessionRepository sessionRepository = new SessionRepository();
    UserMapper userMapper = new UserMapper();
    
    public Optional<Cookie> setupSession(String email, int id) {
        Session session = new Session(email, new Timestamp(System.currentTimeMillis()), id);
        if (sessionRepository.save(session).isPresent()) {
            Cookie cookie = new Cookie("current-user", String.valueOf(session.getId()));
            cookie.setMaxAge(3600);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            return Optional.of(cookie);
            
        } else {
            return Optional.empty();
        }
    }
    
    public Object whoAmI(String sessionId) {
        System.out.println(sessionId + " sessionID");
        if (sessionId == null) {
            return null;
        }
        
        Optional<Session> session = sessionRepository.findById(Integer.parseInt(sessionId));
        if (session.isPresent()) {
            Optional<User> user = userRepository.findById(session.get().getUser_id());
            return userMapper.userToDTO(user);
        }
        return session;
    }
    
    public UserDTO registerUser(User user) {
        String hashedPass = passwordHash.hash(user.getPassword().toCharArray());
        user.setPassword(hashedPass);
        
        if (userRepository.save(user).isPresent()) {
            Optional<Cookie> cookie = setupSession(user.getEmail(), user.getId());
            if (cookie.isPresent()) {
                var findUser = userRepository.findById(user.getId());
                return userMapper.userToDTO(findUser);
            } else {
                return null;
            }
        }
        return null;
    }
    
    public UserDTO loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return null;
        }
        if (passwordHash.authenticate(user.get().getPassword().toCharArray(), password)) {
            var findUser = userRepository.findById(user.get().getId());
            return userMapper.userToDTO(findUser);
        } else {
            return null;
        }
    }
    
    public Cookie setupCookie(String email, int id) {
        Optional<Cookie> cookie = setupSession(email, id);
        if (cookie.isPresent()) {
            System.out.println(cookie.get());
            return cookie.get();
        } else {
            return null;
        }
    }
    
    public void logoutUser(String sessionId) {
        Optional<Session> session = sessionRepository.findById(Integer.parseInt(sessionId));
        session.ifPresent(value -> sessionRepository.deleteById(value));
    }
    
    public UserDTO findUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) return null;
        return userMapper.userToDTO(user);
    }
    
    public String checkIfUserExist(String email) {
        Optional<User> userExists = userRepository.findByEmail(email);
        if (userExists.isPresent()) {
            return null;
        }
        return email;
    }
}
