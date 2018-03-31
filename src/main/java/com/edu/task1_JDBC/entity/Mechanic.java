package com.edu.task1_JDBC.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by damager on 17.08.17.
 */
public class Mechanic implements Serializable {
    private String name;
    private String lastName;
    private BigDecimal salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
