package jpa.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by wil on 07/03/2018.
 */
@Entity
@Table(name = "TABLE_F")
public class F {

    @Id
    @Column(name = "F_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<G> setG;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<G> getSetG() {
        return setG;
    }

    public void setSetG(Set<G> setG) {
        this.setG = setG;
    }
}
