package jpa.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wil on 07/03/2018.
 */
@Entity
@Table(name = "TABLE_A")
public class A {

    enum Value {
        VAL, NO_VAL
    }

    @Id
    @Column(name = "A_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "CREATION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name="STR_FIELD", length = 32)
    private String strField;

    @Column(name="INT_FIELD")
    private Integer intField;

    @Enumerated(EnumType.STRING)
    private Value value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStrField() {
        return strField;
    }

    public void setStrField(String strField) {
        this.strField = strField;
    }

    public Integer getIntField() {
        return intField;
    }

    public void setIntField(Integer intField) {
        this.intField = intField;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my.persistence.unit");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.close();
    }
}
