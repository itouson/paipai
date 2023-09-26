package com.moon.entity;

import java.io.Serializable;
import javax.persistence.*;

public class Type implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String tname;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return tname
     */
    public String getTname() {
        return tname;
    }

    /**
     * @param tname
     */
    public void setTname(String tname) {
        this.tname = tname;
    }
}