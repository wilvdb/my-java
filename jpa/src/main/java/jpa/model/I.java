package jpa.model;

import javax.persistence.*;

/**
 * Created by wil on 07/03/2018.
 */
@Embeddable
public class I {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
