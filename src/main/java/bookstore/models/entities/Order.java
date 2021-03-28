package bookstore.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long number;

    @JsonIgnoreProperties({"smartphones"})
    @ManyToMany
    @JoinTable(
            name="order_smartphones",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "smartphone_id")
    )
    private List<Smartphone> smartphones;

    @Column(nullable = false)
    private BigDecimal fullPrice;

    @Column(nullable = false)
    private Long userId;

    public Order() {
    }

    public Order(Long id, Long number, List<Smartphone> smartphones, BigDecimal fullPrice, Long userId) {
        this.id = id;
        this.number = number;
        this.smartphones = smartphones;
        this.fullPrice = fullPrice;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
