package pl.edu.pk.ztpai_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class AppUser {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password; // Hash using bcrypt
    private String email;

    public AppUser() { }
    public AppUser(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    @Enumerated(EnumType.STRING)
    private Role role; // User

    public Long getId() { return this.id; } 
    public String getUsername() { return this.username; } 
    public String getPassword() { return this.password; } 
    public String getEmail() { return this.email; } 
    public Role getRole() { return this.role; } 

    public void setId(Long id) { this.id = id; } 
    public void setUsername(String username) { this.username = username; } 
    public void setPassword(String password) { this.password = password; } 
    public void setEmail(String email) { this.email = email; } 
    public void setRole(Role role) { this.role = role; } 
}

