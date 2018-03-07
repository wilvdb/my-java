package jpa.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by wil on 07/03/2018.
 */
@Entity
@Table(name = "TABLE_D")
public class D {

    @Id
    @Column(name = "D_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    // 1.
    // @OneToMany(cascade = CascadeType.ALL)
    // 2.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<E> listE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<E> getListE() {
        return listE;
    }

    public void setListE(List<E> listE) {
        this.listE = listE;
    }
}
