package techstore.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Smartphone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String modelName;

    @ManyToOne (optional = false, fetch=FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String cpu;

    @Column(nullable = false)
    private String gpu;

    @Column(nullable = false)
    private Integer ram;

    @Column(nullable = false)
    private Integer rom;

    @Column
    private String description;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="order_smartphones",
            joinColumns = @JoinColumn(name = "smartphone_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<PurchaseOrder> purchaseOrders;

    public Smartphone() {
    }

    public Smartphone(Long id, String modelName, Brand brand, BigDecimal price, String cpu, String gpu, Integer ram, Integer rom, String description, List<PurchaseOrder> purchaseOrders) {
        this.id = id;
        this.modelName = modelName;
        this.brand = brand;
        this.price = price;
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.rom = rom;
        this.description = description;
        this.purchaseOrders = purchaseOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getRom() {
        return rom;
    }

    public void setRom(Integer rom) {
        this.rom = rom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }
}
