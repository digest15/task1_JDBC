package com.edu.task1_JDBC.entity;

import java.io.Serializable;
import java.util.Date;

public abstract class Machine implements Serializable, Identified {
    private Integer id;
    private String namePicking;
    private String vin;
    private Date releaseYear;
    private Color color;
    private Mark mark;
    private TypeCar typeCar;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public TypeCar getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(TypeCar typeCar) {
        this.typeCar = typeCar;
    }

    public String getNamePicking() {
        return namePicking;
    }

    public void setNamePicking(String namePicking) {
        this.namePicking = namePicking;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
