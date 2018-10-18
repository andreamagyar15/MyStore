package ssit.java0.springMVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ssit.java0.springMVC.domain.Information;
import ssit.java0.springMVC.domain.JWTToken;
import ssit.java0.springMVC.dto.UserRepons;
import ssit.java0.springMVC.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600)
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public String showLogin() {
        return "login";
    }

    /**
     * Mapping: Logout the user
     * @param request
     * @param response
     * @return error if not token in header/logout the user
     * @throws IOException
     */
    @RequestMapping(value = "/auth/logout", method = RequestMethod.GET)
    public String doLogout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        String value = request.getHeader("X-Auth-Token");
        if (value == null) {
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            servletResponse.sendError(401,"Error");
        }else{
            userService.logout(value);
        }
        return null;
    }

    /**
     * Mapping: Login the user
     * @param username
     * @param password
     * @param request
     * @param response
     * @return null if the user is not found or userReps object
     * @throws IOException
     */
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    @ResponseBody
    public UserRepons doLogin(String username, String password,
                            HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserRepons userRepons = userService.login(username, password);
        if (userRepons.getJwtToken() == null) {
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            servletResponse.sendError(401,"The user or password is not correct");
            return null;
        } else {
            request.getSession().setAttribute("currentUser", userRepons);
            return userRepons;
        }
    }

    /**
     * Mapping: Register the user, only Guests
     * @param username
     * @param password
     * @param request
     * @param response
     * @return null if the register was with error or the token
     * @throws IOException
     */
    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    @ResponseBody
    public JWTToken doRegister(String username, String password,
                            HttpServletRequest request, HttpServletResponse response) throws IOException {
        JWTToken token = userService.register(username, password);
        if (token == null) {
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            servletResponse.sendError(401,"The user exists");
            return null;
        } else {
            request.getSession().setAttribute("currentUser", token);
            return token;
        }
    }
}
