package pl.edu.pk.ztpai_project.service;

import org.springframework.stereotype.Service;
import pl.edu.pk.ztpai_project.model.AppUser;
import pl.edu.pk.ztpai_project.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser createUser(AppUser user) {
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }

        return userRepository.save(user);
    }

    public AppUser getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

