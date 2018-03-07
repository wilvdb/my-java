package jpa.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by wil on 07/03/2018.
 */
@Entity
@Table(name = "TABLE_H")
public class H {

    @Id
    @Column(name = "H_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Embedded
    private I i;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
