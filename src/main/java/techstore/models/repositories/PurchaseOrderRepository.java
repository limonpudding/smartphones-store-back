package techstore.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import techstore.models.entities.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    List<PurchaseOrder> findPurchaseOrdersByUserId(Long userId);
}
