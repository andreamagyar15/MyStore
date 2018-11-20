package ssit.java0.springMVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ssit.java0.springMVC.DAO.UserDAO;
import ssit.java0.springMVC.domain.JWTToken;
import ssit.java0.springMVC.domain.Role;
import ssit.java0.springMVC.domain.User;
import ssit.java0.springMVC.dto.UserReponse;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserDAO userDAO;

    /**
     * Login, checks if the user existsin DB, sets a token and check the role
     * @param username
     * @param pass
     * @return UserRespons object with the role and the token
     */
    @Override
    public UserReponse login(String username, String pass) {
        User  user = new User();
        user.setUsername(username);
        user.setPassword(pass);

        if(userDAO.checkUser(user)==null){
            return null;
        }else{
            JWTToken token=new JWTToken(username);
            userDAO.setTokenForUser(username,token.getToken());
            String role=userDAO.checkCredential(token.getToken());
            UserReponse userRepons=new UserReponse(Role.valueOf(role),token.getToken());
            return userRepons;
        }
    }

    /**
     * Logout the user, delete the token from DB
     * @param token
     */
    @Override
    public void logout(String token) {
      userDAO.deleteTokenForUser(token);
    }

    /**
     * Register the user, if it does not exists
     * @param username
     * @param pass
     * @return the new token
     */
    @Override
    public JWTToken register(String username, String pass) {
        User  userReg = new User();
        userReg.setUsername(username);
        userReg.setPassword(pass);
        userReg.setRole(Role.valueOf("GUEST"));
        JWTToken token=new JWTToken(username);
       if(userDAO.checkUserNameInDB(userReg)==null) {
           try {
               userDAO.register(userReg, token);
               return token;
           } catch (Exception e) {
               return null;
           }
       }
        else{
            return null;
       }
    }

    /**
     * Check the user credentail
     * @param token
     * @return the user role
     */
    @Override
    public Role checkUserCredential(String token) {
        try {
            String credential = userDAO.checkCredential(token);
            return Role.valueOf(credential);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
