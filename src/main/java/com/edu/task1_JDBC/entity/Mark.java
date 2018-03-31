package com.edu.task1_JDBC.entity;

import java.io.Serializable;

public class Mark implements Serializable, Identified {
    private Integer id;
    private String manufacturer;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
