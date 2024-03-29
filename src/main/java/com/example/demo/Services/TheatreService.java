package com.example.demo.Services;

import com.example.demo.Seat;
import com.example.demo.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TheatreService {

    // Assuming you have a map to store Theatre objects by their IDs
    private static final Map<String, Theatre> theatreMap = new HashMap<>();

    // Method to add Theatre objects to the map
    public static void addTheatre(Theatre theatre) {
        theatreMap.put(theatre.getName(), theatre);
    }

    // Method to retrieve a Theatre object by its ID
    public static Theatre getTheatreByName(String theatreName) {
//        System.out.println(theatreMap);
        return theatreMap.get(theatreName);
    }

    public void addSeatsForTheatre(Theatre theatre) {
//        System.out.println(theatre.getSeatCapacity()+" "+theatre.getName() +"in TheatreService");
        int seatCapacity = Integer.parseInt(theatre.getSeatCapacity());
        List<Seat> seatList = new ArrayList<>();
        for (int i = 1; i <= seatCapacity; i++) {
            Seat seat = new Seat("S" + i, true); // Assuming seats are initially available
            seatList.add(seat);
            seat.setTheatre(theatre); // Set the theater for the seat
             // Save the seat to the database
        }
        theatre.setSeatList(seatList);
    }
}
