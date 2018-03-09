package jpa.model;

import javax.persistence.*;

/**
 * Created by wil on 09/03/2018.
 */
// 1. create subclass : @MappedSuperclass
@Entity
//2. table per class : @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//3. single table : @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//3. @DiscriminatorColumn(name = "letter")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="TABLE_J")
public class J {

    @Id
    @Column(name="ID_J")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
