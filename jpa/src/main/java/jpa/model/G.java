package jpa.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by wil on 07/03/2018.
 */
@Entity
@Table(name = "TABLE_G")
public class G {

    @Id
    @Column(name = "G_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<F> setF;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<F> getSetF() {
        return setF;
    }

    public void setSetF(Set<F> setF) {
        this.setF = setF;
    }
}
