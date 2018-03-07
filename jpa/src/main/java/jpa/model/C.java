package jpa.model;

import javax.persistence.*;

/**
 * Created by wil on 07/03/2018.
 */
@Entity
@Table(name = "TABLE_C")
public class C {

    @Id
    @Column(name = "C_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
