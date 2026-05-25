package pl.edu.pk.ztpai_project.dto.User;

public class RegisterRequest {

    private String username;
    private String password;
    private String email;
    private String role;

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getEmail()    { return this.email;    }
    public String getRole()     { return this.role;     }
}
