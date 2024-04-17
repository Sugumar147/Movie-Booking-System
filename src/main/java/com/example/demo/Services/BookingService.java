package com.example.demo.Services;

import com.example.demo.mapper.BookingMapper;
import com.example.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private BookingMapper bookingMapper;

    @Autowired
    public BookingService(BookingMapper bookingMapper) {
        this.bookingMapper = bookingMapper;
    }

    public void bookSeats(String theatreName, String timing,Ticket ticket,String userName) {
        String[] seatNumbersArray = ticket.getSeatNumbers().toArray(new String[ticket.getSeatNumbers().size()]);
        bookingMapper.insertBooking(userName,ticket.getBookingId(),ticket.getMovie(),ticket.getTheatre(),
                ticket.getTiming(),seatNumbersArray,ticket.getAmount());
    }
    public List<String> getOccupiedSeats(String theatreName, String timing) {
        // Fetch occupied seat numbers for the given theatre and timing from the database
        String input = String.valueOf(bookingMapper.getOccupiedSeats(theatreName, timing));
        String cleanedString = input.replaceAll("[\\[\\]{}]", "");

        // Split the string by commas to get individual seat strings
        String[] seatArray = cleanedString.split(",");

        // Remove leading and trailing spaces from each seat string
        List<String> cleanedSeats = Arrays.stream(seatArray)
                .map(String::trim)
                .collect(Collectors.toList());

        // Optionally, convert each seat string to lowercase
        // cleanedSeats = cleanedSeats.stream().map(String::toLowerCase).collect(Collectors.toList());

        return cleanedSeats;
    }
}
