package jpa.model;

import javax.persistence.*;

/**
 * Created by wil on 09/03/2018.
 */
@Entity
@Table(name="TABLE_K")
public class K extends J {

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
