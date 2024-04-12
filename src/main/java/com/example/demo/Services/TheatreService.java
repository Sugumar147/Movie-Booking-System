package com.example.demo.Services;

import com.example.demo.model.Seat;
import com.example.demo.model.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TheatreService {
    @Autowired
    BookingService bookingService;
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

    public void addSeatsForTheatre(Collection<Theatre> theatreList) {
//        System.out.println(theatre.getSeatCapacity()+" "+theatre.getName() +"in TheatreService");
        for(Theatre theatre:theatreList) {
            int seatCapacity = Integer.parseInt(theatre.getSeatCapacity());
            List<Seat> seatList = new ArrayList<>();
            for (int i = 1; i <= seatCapacity; i++) {
                Seat seat = new Seat("S" + i, false); // Assuming seats are initially available
                seatList.add(seat);
                seat.setTheatre(theatre); // Set the theater for the seat
                // Save the seat to the database
            }
            theatre.setSeatList(seatList);
        }
    }

    public int getSeatsAvailable(Theatre theatre) {
        int count=0;
        List<Seat> seatList = theatre.GetSeatList();
        for(Seat seat : seatList) {
            if(!seat.isOccupied()) {
                count++;
            }
        }
        return count;
    }
    public void occupySeats(Theatre theatre, List<String> seatNumbers, String timing) {
        List<Seat> seatList = theatre.GetSeatList();
        for (Seat seat : seatList) {
            if (seatNumbers.contains(seat.getSeatNumber())) {
                seat.setOccupied(true);
                // Optionally, you may want to save the updated seat information to the database here
                bookingService.bookSeats(theatre.getName(), timing, seat.getSeatNumber() );
                System.out.println(theatre.getName()+"  " + timing +" " + seat.getSeatNumber() );
            }
        }
    }

}
