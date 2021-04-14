package techstore.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Brand {

    @Id
    @SequenceGenerator(name = "brandIdSeq", sequenceName = "BRAND_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brandIdSeq")
    private Long id;

    @Column(nullable = false)
    private String name;

    public Brand() {
    }

    @JsonIgnore
    @OneToMany (mappedBy="brand", fetch=FetchType.LAZY)
    private Collection<Smartphone> smartphones;

    public Brand(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Smartphone> getSmartphones() {
        return smartphones;
    }

    public void setSmartphones(Collection<Smartphone> smartphones) {
        this.smartphones = smartphones;
    }
}
