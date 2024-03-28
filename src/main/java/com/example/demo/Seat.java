package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Seat {
    private String seatNumber;
    private boolean isOccupied;

    private Theatre theatre;
    public Seat() {

    }
    public Seat(String seatNumber, boolean isOccupied) {
        this.seatNumber = seatNumber;
        this.isOccupied = isOccupied;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }
}
