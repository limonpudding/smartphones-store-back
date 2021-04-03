package techstore.models.repositories;

import techstore.models.entities.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmartphoneRepository extends JpaRepository<Smartphone, Long> {

    List<Smartphone> findSmartphonesByBrand_Id(Long brand_id);

}
