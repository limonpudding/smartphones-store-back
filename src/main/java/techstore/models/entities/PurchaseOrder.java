package techstore.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import techstore.models.enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class PurchaseOrder {

    @Id
    @SequenceGenerator(name = "purchaseOrderIdSeq", sequenceName = "PURCHASE_ORDER_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchaseOrderIdSeq")
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    @JsonIgnoreProperties({"purchaseOrders"})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="order_smartphones",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "smartphone_id")
    )
    private List<Smartphone> smartphones;

    @Column(nullable = false)
    private BigDecimal fullPrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = false)
    private Long userId;

    @Transient
    private String userName;

    public PurchaseOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Smartphone> getSmartphones() {
        return smartphones;
    }

    public void setSmartphones(List<Smartphone> smartphones) {
        this.smartphones = smartphones;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
