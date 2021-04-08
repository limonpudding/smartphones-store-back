package techstore.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import techstore.models.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUserName(String userName);
}
