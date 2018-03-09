package jpa.model;

import javax.persistence.*;

/**
 * Created by wil on 09/03/2018.
 */
@Entity
@Table(name="TABLE_L")
public class L extends J {

    @Column
    private String surname;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
