package schoolmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import schoolmanagementsystem.util.Role;

import java.util.List;

public class SignupRequest {
    private String username;
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private List<Role> role;

    public SignupRequest() {}

    public SignupRequest(String username, String password, List<Role> role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRole() {
        return role;
    }
}
