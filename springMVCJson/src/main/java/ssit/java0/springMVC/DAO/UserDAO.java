package ssit.java0.springMVC.DAO;

import ssit.java0.springMVC.domain.JWTToken;
import ssit.java0.springMVC.domain.User;

import java.util.List;

public interface UserDAO {
    public User checkUser(User user);

    void setTokenForUser(String username, String token);

    void deleteTokenForUser(String token);
    void register(User user, JWTToken token);
    String checkCredential(String token);
    User checkUserNameInDB(User user);

}