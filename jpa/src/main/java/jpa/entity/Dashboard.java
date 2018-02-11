package jpa.entity;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Created by wil on 11/02/2018.
 */
@Entity
public class Dashboard {

    private long id;
    private String name;
    private Date creationDate;
    private List<Stage> stages;
    private List<Line> lines;
}
