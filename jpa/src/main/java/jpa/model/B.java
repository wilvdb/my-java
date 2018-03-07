package jpa.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by wil on 07/03/2018.
 */
@Entity
@Table(name = "TABLE_B")
public class B {

    @Id
    @Column(name="B_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private C c;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }
}
