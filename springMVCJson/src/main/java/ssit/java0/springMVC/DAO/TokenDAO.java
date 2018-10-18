package ssit.java0.springMVC.DAO;

import ssit.java0.springMVC.domain.User;

import java.util.List;

public interface TokenDAO {
    public List<User> getToken(String username, String token);
    public String isValidToken(String token);
}
