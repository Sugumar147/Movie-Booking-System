package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.List;


public class Theatre {
    private int id;
    private String name;
    private String seatCapacity;
    private List<String> MoviesList;
    private List<Seat> seatList;

    public Theatre() {

    }

    public Theatre(int id, String name, String seatCapacity, List<String> moviesList) {
        this.id = id;
        this.name = name;
        this.seatCapacity = seatCapacity;
        MoviesList = moviesList;
    }

    public Theatre(int id, String name, String seatCapacity, List<String> moviesList, List<Seat> seatList) {
        this.id = id;
        this.name = name;
        this.seatCapacity = seatCapacity;
        MoviesList = moviesList;
        this.seatList = seatList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(String seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public List<Seat> GetSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public List<String> getMoviesList() {
        return MoviesList;
    }

    public void setMoviesList(List<String> moviesList) {
        MoviesList = moviesList;
    }

    // Method to get total available seats
    public int getTotalSeats() {
        return Integer.parseInt(seatCapacity);
    }
}
