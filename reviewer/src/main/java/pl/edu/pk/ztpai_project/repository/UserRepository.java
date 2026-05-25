package pl.edu.pk.ztpai_project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pk.ztpai_project.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
