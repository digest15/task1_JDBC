package com.edu.task1_JDBC.entity;

public class Bus extends Machine {
    private int numberPassengerSeats;
        private int numberPassengerStanding;

    public int getNumberPassengerSeats() {
        return numberPassengerSeats;
    }

    public void setNumberPassengerSeats(int numberPassengerSeats) {
        this.numberPassengerSeats = numberPassengerSeats;
    }

    public int getNumberPassengerStanding() {
        return numberPassengerStanding;
    }

    public void setNumberPassengerStanding(int numberPassengerStanding) {
        this.numberPassengerStanding = numberPassengerStanding;
    }
}
