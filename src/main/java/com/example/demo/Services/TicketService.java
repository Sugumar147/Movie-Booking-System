package com.example.demo.Services;

import com.example.demo.config.TicketConfig;
import com.example.demo.model.Seat;
import com.example.demo.model.Ticket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    private TicketConfig ticketConfig;
    @Autowired
    private HttpServletRequest request;
    public Ticket generateTicket() {
        Ticket ticket = ticketConfig.ticket();
        HttpSession session =  request.getSession();
        ticket.setTheatre((String) session.getAttribute("selectedTheatre"));
        ticket.setMovie((String) session.getAttribute("movieName"));
        ticket.setSeatList((List<Seat>) session.getAttribute("selectedSeats"));
        ticket.setTiming((String) session.getAttribute("timing"));
        session.setAttribute("seats",(List<Seat>) session.getAttribute("selectedSeats"));
        ticket.setAmount(((Integer) session.getAttribute("totalSeats"))*200);
        String randomString = generateRandomString();
        ticket.setBookingId(randomString);
        return ticket;
    }
    private static String generateRandomString() {
        // Generate a random UUID (Universally Unique Identifier)
        UUID uuid = UUID.randomUUID();
        // Convert UUID to a string and remove hyphens
        String randomString = uuid.toString().replace("-", "");
        // Return a substring of the first 16 characters
        return (randomString.substring(0, 8)).toUpperCase();
    }
}
