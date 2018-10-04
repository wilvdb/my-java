package jnosql.entity;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Embeddable;

@Embeddable
public class Address {

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private Integer number;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
