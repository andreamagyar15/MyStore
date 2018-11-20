package ssit.java0.springMVC.service;

import ssit.java0.springMVC.domain.JWTToken;
import ssit.java0.springMVC.domain.Role;
import ssit.java0.springMVC.dto.UserReponse;


public interface UserService {
    UserReponse login(String user, String pass);
    void logout(String token);
    JWTToken register(String user, String pass);
    Role checkUserCredential(String token);
}
