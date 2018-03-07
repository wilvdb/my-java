package jpa.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by wil on 07/03/2018.
 */
@Entity
@Table(name = "TABLE_E")
public class E {

    @Id
    @Column(name = "E_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    // 3.
    @ManyToOne
    private D d;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public D getD() {
        return d;
    }

    public void setD(D d) {
        this.d = d;
    }
}
