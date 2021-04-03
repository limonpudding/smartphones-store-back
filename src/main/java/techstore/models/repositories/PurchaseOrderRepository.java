package techstore.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import techstore.models.entities.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
