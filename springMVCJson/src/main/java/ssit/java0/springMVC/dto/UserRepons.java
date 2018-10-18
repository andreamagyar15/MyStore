package ssit.java0.springMVC.dto;

import ssit.java0.springMVC.domain.Role;

public class UserRepons {
    private Role role;
    private String jwtToken;

    public UserRepons(Role role, String jwtToken) {
        this.role = role;
        this.jwtToken = jwtToken;
    }

    public String getRole() {
        return role.name();
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }


}
