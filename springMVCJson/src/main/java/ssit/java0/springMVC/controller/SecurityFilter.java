package ssit.java0.springMVC.controller;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ssit.java0.springMVC.domain.Role;
import ssit.java0.springMVC.service.SecurityServiceImpl;
import ssit.java0.springMVC.service.TokenService;
import ssit.java0.springMVC.service.TokenServiceImpl;
import ssit.java0.springMVC.service.UserService;
//@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600)

/**
 * Filte for requests. If the url contains "secure" and token
 * Check: the if the token is in DB and the user is an admin
 */
@Component
public class SecurityFilter implements Filter {

    @Autowired
    private SecurityServiceImpl securityService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    static final String TOKEN = "X-Auth-Token";

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        //chain.doFilter(request, response);

        String url = ((HttpServletRequest) request).getRequestURL().toString();
        if (url.contains("secured")) {
            //if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
            // HttpServletResponse servletResponse = (HttpServletResponse) response;
            //servletResponse.setHeader("Access-Control-Allow-Origin", "*");
            // servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            //  servletResponse.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, HEAD");
            //   servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization,X-Auth-Token");
            //   servletResponse.setStatus(HttpServletResponse.SC_OK);
            //} else {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String value = httpRequest.getHeader(TOKEN);
            //System.out.println(value);
            String username = httpRequest.getHeader("Username");
            if (value != null) {
                boolean valid = tokenService.isValidToken(value);
                Role userRole = userService.checkUserCredential(value);
                if (valid && Role.valueOf("ADMIN") == userRole) {
                    HttpServletResponse servletResponse = (HttpServletResponse) response;
                    //servletResponse.sendError(401);
                    //servletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                    //servletResponse.setHeader("role", "ADMIN");
                } else {
                    HttpServletResponse servletResponse = (HttpServletResponse) response;
                    servletResponse.sendError(403);
                    return;
                }
            } else {
                HttpServletResponse servletResponse = (HttpServletResponse) response;
                servletResponse.sendError(404);
                return;
            }
            //}
        }
            chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }
}
