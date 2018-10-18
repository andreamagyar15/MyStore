package ssit.java0.springMVC.service;

import org.springframework.stereotype.Service;
import ssit.java0.springMVC.domain.User;
@Service
public class SecurityServiceImpl {
    private ThreadLocal<User> currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = new ThreadLocal<>();
        this.currentUser.set(user);
    }

    public User getCurrentUser() {
        return this.currentUser != null ?
                this.currentUser.get() : null;

    }
}
