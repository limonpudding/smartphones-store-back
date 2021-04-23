package techstore.models;

import techstore.models.enums.UserRole;

import java.util.List;

public class JwtCredentials {

    private Long id;

    private String userName;

    private List<String> roles;

    private String token;

    public JwtCredentials(Long id, String userName, List<String> roles, String token) {
        this.id = id;
        this.userName = userName;
        this.roles = roles;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
