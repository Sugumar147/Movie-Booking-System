package com.example.demo.Services;

import com.example.demo.mapper.BookingMapper;
import com.example.demo.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private BookingMapper bookingMapper;

    @Autowired
    public BookingService(BookingMapper bookingMapper) {
        this.bookingMapper = bookingMapper;
    }

    public void bookSeats(String theatreName, String timing, String seatNumber) {
        Booking booking = new Booking();
        booking.setTheatreName(theatreName);
        booking.setTiming(timing);
        booking.setSeatNumber(seatNumber);
        bookingMapper.insertBooking(booking);
    }
    public List<String> getOccupiedSeats(String theatreName, String timing) {
        // Fetch occupied seat numbers for the given theatre and timing from the database
        return bookingMapper.getOccupiedSeats(theatreName,timing);
    }
}
