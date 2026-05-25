package pl.edu.pk.ztpai_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.pk.ztpai_project.model.AppUser;
import pl.edu.pk.ztpai_project.model.Role;
import pl.edu.pk.ztpai_project.repository.UserRepository;
import pl.edu.pk.ztpai_project.security.JwtUtil;
import pl.edu.pk.ztpai_project.service.UserService;
import pl.edu.pk.ztpai_project.dto.User.AuthResponse;
import pl.edu.pk.ztpai_project.dto.User.LoginRequest;
import pl.edu.pk.ztpai_project.dto.User.RegisterRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService service, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> request(@RequestBody RegisterRequest req) {
        AppUser user = new AppUser();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setEmail(req.getEmail());
        user.setRole(Role.valueOf(req.getRole()));
        service.createUser(user);
        return ResponseEntity.ok("User reqistered");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        AppUser user = service.findByUsername(req.getUsername()).orElseThrow();
        String token = jwtUtil.generateToken(user.getId(), user.getUsername(), user.getRole().name());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
