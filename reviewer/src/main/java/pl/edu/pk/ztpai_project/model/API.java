package pl.edu.pk.ztpai_project.model;

import jakarta.persistence.*;

@Entity
public class API {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private AppUser user;

	@Enumerated(EnumType.STRING)
    private API_TIER role;

    private String api_key;

    public void setId(Long id) { this.id = id; }
	public void setUser(AppUser user) { this.user = user; }
    public void setRole(API_TIER role) { this.role = role; }
    public void setApi_key(String api_key) { this.api_key = api_key; }

    public Long getId() { return id; }
    public AppUser getUser() { return user; }
	public API_TIER getRole() { return role; }
    public String getApi_key() { return api_key; }
}
