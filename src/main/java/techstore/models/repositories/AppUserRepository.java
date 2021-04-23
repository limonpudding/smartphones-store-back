package techstore.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import techstore.models.entities.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUserName(String userName);
    Boolean existsByUserName(String userName);
}
