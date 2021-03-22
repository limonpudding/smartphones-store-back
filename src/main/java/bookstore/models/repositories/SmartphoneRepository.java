package bookstore.models.repositories;

import bookstore.models.entities.Smartphone;
import org.springframework.data.repository.CrudRepository;

public interface SmartphoneRepository extends CrudRepository<Smartphone, Long> {
}
