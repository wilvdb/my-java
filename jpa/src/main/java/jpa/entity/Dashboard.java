package jpa.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.zip.DataFormatException;

/**
 * Created by wil on 11/02/2018.
 */
@NamedQuery(name="dashboardStages", query="select d.stages from Dashboard d where d.id = :id")
@Entity
public class Dashboard {

    private Long id;
    private String name;
    private Date creationDate;
    private Set<Stage> stages;

    @Id
    @Column(name = "DASHBOARD_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

}
