package bookstore.models.repositories;

import bookstore.models.entities.Brand;
import bookstore.models.entities.Smartphone;
import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<Brand, Long> {
}
