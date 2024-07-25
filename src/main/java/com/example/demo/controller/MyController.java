package com.example.demo.controller;

import com.example.demo.Services.*;
import com.example.demo.mapper.BookingMapper;
import com.example.demo.mapper.UsersMapper;
import com.example.demo.model.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private MovieService movieService;

    @Autowired
    private TheatreService theatreService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private HttpSession session;

    @GetMapping("/theatrelist")
    public ModelAndView theatrelist(@RequestParam(required = false) String movie) {
        ModelAndView mv = new ModelAndView();
        if(session.getAttribute("userName") == null) {
            mv.setViewName("redirect:/");
            return mv;
        }
        if (movie == null) {
            mv.setViewName("redirect:/");
            return mv;
        }
        session.setAttribute("movieName", movie);
        mv.setViewName("theatrelist");
        return mv;
    }

    @GetMapping("/booking")
    public ModelAndView booking(@RequestParam(required = false) String theatreName, String timing) {
        ModelAndView mv = new ModelAndView();
        if(session.getAttribute("userName") == null) {
            mv.setViewName("redirect:/");
            return mv;
        }
        if (theatreName == null) {
            mv.setViewName("redirect:/");
            return mv;
        }
        Theatre theatre = theatreService.getTheatreByName(theatreName);
        List<String> occupiedSeats = bookingService.getOccupiedSeats(theatreName, timing);
        List<Seat> seatList = theatre.GetSeatList();
        mv.setViewName("booking");
        mv.addObject("occupiedSeats", occupiedSeats);
        mv.addObject("seatList", seatList);
        session.setAttribute("theatre", theatre);
        session.setAttribute("timing", timing);
        session.setAttribute("selectedTheatre", theatreName);
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView login(String username, String password, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        if (userService.verifyLogin(username, password)) {
            mv.setViewName("payment");
            session.setAttribute("userName", username);
            session.setAttribute("loggedInUser", username);
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
            List<String> selectedSeats = (List<String>) session.getAttribute("selectedSeats");
            String seats = String.join(", ", selectedSeats);
            mv.setViewName("redirect:/confirmbooking?selectedSeats=" + seats);
        }
        return mv;
    }

    @GetMapping("/bookingsuccess")
    public ModelAndView bookingSuccess() throws InterruptedException {
        ModelAndView mv = new ModelAndView();
        String userName = (String) session.getAttribute("userName");
        if (userName == null) {
            mv.setViewName("redirect:/");
            return mv;
        }
        mv.setViewName("bookingsuccess");
        Ticket ticket = ticketService.generateTicket();
        mv.addObject("ticket", ticket);
        session.setAttribute("ticket", ticket);
        Theatre theatre = (Theatre) session.getAttribute("theatre");
        String timing = (String) session.getAttribute("timing");
        List<String> selectedSeats = (List<String>) session.getAttribute("selectedSeats");
        theatreService.occupySeats(theatre, selectedSeats, timing, ticket, userName);
        return mv;
    }

    @PostMapping("/secondarylogin")
    public ModelAndView secondarylogin(String username, String password) {
        ModelAndView mv = new ModelAndView();
        if (username != null && password != null && userService.verifyLogin(username, password)) {
            List<Ticket> tickets = bookingMapper.getAllBookingsByUserName(username);
            mv.addObject("tickets", tickets);
            mv.setViewName("mybookings");
            session.setAttribute("userName", username);
            session.setAttribute("loggedInUser", username);
        } else {
            mv.setViewName("login");
        }
        return mv;
    }

    @PostMapping("/confirmbooking")
    public ModelAndView confirmBooking(@RequestParam(required = false) List<String> selectedSeats) {
        ModelAndView mv = new ModelAndView();
        Theatre theatre = (Theatre) session.getAttribute("theatre");
        String selectedTheatre = (String) session.getAttribute("selectedTheatre");
        String timing = (String) session.getAttribute("timing");
        if(session.getAttribute("userName") == null) {
            mv.setViewName("redirect:/");
            return mv;
        }
        if (selectedSeats == null) {
            mv.setViewName("redirect:/");
            return mv;
        }
        if (selectedSeats == null) {
            mv.setViewName("redirect:/booking?theatreName=" + selectedTheatre + "&timing=" + timing);
        } else {
            int totalSeats = selectedSeats.size();
            mv.addObject("totalSeats", totalSeats);
            session.setAttribute("totalSeats", totalSeats);
            mv.addObject("selectedSeats", selectedSeats);
            session.setAttribute("selectedSeats", selectedSeats);
            String loggedInUser = (String) session.getAttribute("loggedInUser");
            if (loggedInUser != null && !loggedInUser.isEmpty()) {
                mv.setViewName("payment");
            } else {
                mv.setViewName("confirmbooking");
            }
        }
        return mv;
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
        return new ModelAndView("signup");
    }

    @GetMapping("/mybookings")
    public ModelAndView myBookings() {
        ModelAndView mv = new ModelAndView();
        String userName = (String) session.getAttribute("userName");
        if (userName == null) {
            mv.setViewName("login");
            return mv;
        }
        List<Ticket> tickets = bookingMapper.getAllBookingsByUserName(userName);
        mv.addObject("tickets", tickets);
        mv.setViewName("mybookings");
        return mv;
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        ModelAndView mv = new ModelAndView();
        if (session != null) {
            session.invalidate();
        }
        mv.setViewName("redirect:/loggedout.html");
        return mv;
    }

    @PostMapping("/payment")
    public ModelAndView payment(@Valid @ModelAttribute("user") User user, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            mv.setViewName("signup");
            return mv;
        }
        List<String> seatList = (List<String>) session.getAttribute("selectedSeats");
        if(session.getAttribute("userName") == null) {
            mv.setViewName("redirect:/");
            return mv;
        }
        if (seatList == null) {
            mv.setViewName("redirect:/mybookings");
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("loggedInUser", user.getUserName());
            userService.insertUser(user);
            session.setAttribute("user", user);
            return mv;
        }
        mv.setViewName("payment");
        session.setAttribute("userName", user.getUserName());
        userService.insertUser(user);
        session.setAttribute("user", user);
        session.setAttribute("loggedInUser", user.getUserName());
        return mv;
    }

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        if (session.getAttribute("userName") == null) {
            mv.setViewName("login");
        } else {
            mv.setViewName("home");
        }
        return mv;
    }
}
