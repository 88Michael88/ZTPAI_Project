package pl.edu.pk.ztpai_project.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import pl.edu.pk.ztpai_project.model.API;
import pl.edu.pk.ztpai_project.model.API_TIER;
import pl.edu.pk.ztpai_project.model.AppUser;
import pl.edu.pk.ztpai_project.repository.API_Repository;
import pl.edu.pk.ztpai_project.repository.UserRepository;

@Service
public class API_Service {

    private final API_Repository repository;
    private final UserRepository userRepository;

    public API_Service(
            API_Repository repository,
            UserRepository userRepository) {

        this.repository = repository;
        this.userRepository = userRepository;
    }

    public String generateApiKey(Long userId) {

        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String apiKey =
                UUID.randomUUID().toString().replace("-", "") +
                UUID.randomUUID().toString().replace("-", "");

        API api = new API();

        api.setUser(user);
        api.setApi_key(apiKey);

        // The only TIER for now.
        api.setRole(API_TIER.FREE);

        repository.save(api);

        return apiKey;
    }
}
