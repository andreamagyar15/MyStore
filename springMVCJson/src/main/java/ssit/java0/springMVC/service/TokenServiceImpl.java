package ssit.java0.springMVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssit.java0.springMVC.DAO.TokenDAO;
import ssit.java0.springMVC.DAO.TokenDAOImpl;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenDAO tokenDAO=new TokenDAOImpl();
    @Override
    public boolean isValidToken(String token){
        try {
            if (tokenDAO.isValidToken(token) == null) {
                return false;
            } else {
                return true;
            }
        }catch (Exception e){
            return false;
        }
    }
}
