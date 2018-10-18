package ssit.java0.springMVC.domain;

import java.util.List;

public class User {
    private String userName;
    private String password;
    private Role role;
    public User(){

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        //this.role = role;
    }
    public User(String userName){
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
