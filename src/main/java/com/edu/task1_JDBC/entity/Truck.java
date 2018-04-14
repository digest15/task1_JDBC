package com.edu.task1_JDBC.entity;

public class Truck extends Machine {
    private int numberAxle;
    private int capacity;

    public Truck() {
        setTypeCar(TypeCar.Truck);
    }

    public int getNumberAxle() {
        return numberAxle;
    }

    public void setNumberAxle(int numberAxle) {
        this.numberAxle = numberAxle;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
