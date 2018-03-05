package jpa.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by wil on 11/02/2018.
 */
@Entity
public class Stage {

    private Long id;
    private String name;
    private int workInProgress;

    @Id
    @Column(name = "ID_STAGE")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    @Column(name = "NAME", nullable = false)
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Column(name="WIP")
    public int getWorkInProgress() { return workInProgress; }

    public void setWorkInProgress(int workInProgress) { this.workInProgress = workInProgress; }

}
