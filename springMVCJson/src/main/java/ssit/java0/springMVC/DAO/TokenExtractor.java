package ssit.java0.springMVC.DAO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600)
@WebServlet("/RequestHeaderServlet")
public class TokenExtractor {
    /**
     * Extract token from request header
     * @param request
     * @return token
     */
    public String extract(HttpServletRequest request){
        String token=request.getHeader("X-Auth-Token");
        if (token == null) {
            System.out.println("Token not found in headers. Trying request parameters.");
            return null;
        }
            else {
        return token;
    }
    }
}
