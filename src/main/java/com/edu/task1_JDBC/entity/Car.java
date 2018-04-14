package com.edu.task1_JDBC.entity;

import java.util.List;

public class Car extends Machine {
    private int numberPassengerSeats;

    public Car() {
        setTypeCar(TypeCar.Car);
    }

    public int getNumberPassengerSeats() {
        return numberPassengerSeats;
    }

    public void setNumberPassengerSeats(int numberPassengerSeats) {
        this.numberPassengerSeats = numberPassengerSeats;
    }

}
