package jpa.entity;


import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by wil on 10/02/2018.
 */
@Entity
public class Card {

    private long id;
    private String name;
    private Date creationDate;
    private Line line;
    private Stage currentStage;
    private boolean done;
    private Date dueDate;

    @Id
    @Column(name="TASK_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
