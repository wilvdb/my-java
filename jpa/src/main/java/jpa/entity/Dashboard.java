package jpa.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.zip.DataFormatException;

/**
 * Created by wil on 11/02/2018.
 */
@Entity
public class Dashboard {

    private long id;
    private String name;
    private Date creationDate;
    private Set<Stage> stages;
    private Set<Line> lines;

    @Id
    @Column(name = "DASHBOARD_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

@Column(name="NAME",unique = true, nullable=false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CREATION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Stage> getStages() {
        return stages;
    }

    public void setStages(Set<Stage> stages) {
        this.stages = stages;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    public Set<Line> getLines() {
        return lines;
    }

    public void setLines(Set<Line> lines) {
        this.lines = lines;
    }
}
