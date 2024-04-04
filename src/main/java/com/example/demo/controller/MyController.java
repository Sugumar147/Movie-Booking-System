package com.example.demo.controller;

import com.example.demo.Services.*;
import com.example.demo.mapper.UsersMapper;
import com.example.demo.model.*;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@MappedTypes(User.class)
@MapperScan("com.example.demo.mapper")
@Controller
public class MyController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private MovieService movieService;
    @Autowired
    private TheatreService theatreService;
    @Autowired
    private TicketService ticketService;
    @GetMapping("/theatrelist")
    public ModelAndView theatrelist(String movie  , HttpSession session) {
        ModelAndView mv = new ModelAndView();
        session.setAttribute("movieName" , movie);
        mv.setViewName("theatrelist");
        return mv;
    }
    @GetMapping("/booking")
    public ModelAndView booking(@RequestParam("theatreName") String selectedTheatre ,String timing,  HttpSession session) {
        Theatre theatre = TheatreService.getTheatreByName(selectedTheatre);
        List<String> occupiedSeats = bookingService.getOccupiedSeats(selectedTheatre,timing);
        System.out.println(occupiedSeats);
//        theatreService.addSeatsForTheatre(theatre);
        List<Seat> seatList = theatre.GetSeatList();
        ModelAndView mv = new ModelAndView("booking");
        mv.addObject("seatList", seatList);
        session.setAttribute("theatre",theatre );
        session.setAttribute("timing",timing);
        mv.addObject("selectedTheatre", selectedTheatre);
        session.setAttribute("selectedTheatre", selectedTheatre);
        return mv;
    }
    @RequestMapping("/login")
    public ModelAndView login(String username,String password,  RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        if(userService.verifyLogin(username,password)) {
            mv.setViewName("payment");
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
            mv.setViewName("redirect:/confirmbooking");
        }
        return mv;
    }

    @RequestMapping("/bookingsuccess")
    public ModelAndView bookingSuccess(HttpSession session)  throws InterruptedException{
        ModelAndView mv = new ModelAndView("bookingsuccess");
        Ticket ticket = ticketService.generateTicket();
        mv.addObject("ticket",ticket);
        session.setAttribute("ticket",ticket);
        Theatre theatre = (Theatre) session.getAttribute("theatre");
        String timing = (String) session.getAttribute("timing");
        List<String> selectedSeats = (List<String>) session.getAttribute("selectedSeats");
        theatreService.occupySeats(theatre,selectedSeats,timing);
        return mv;
    }
    @RequestMapping("/confirmbooking")
    public ModelAndView confirmBooking(@Nullable @RequestParam("selectedSeats") List<String> selectedSeats,HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String selectedTheatre = (String) session.getAttribute("selectedTheatre");
        if(selectedSeats==null) {
            mv.setViewName("redirect:/booking?theatreName=" + selectedTheatre);
        }
        else {
            int totalSeats = selectedSeats.size();
            Theatre theatre = (Theatre) session.getAttribute("theatre");
            mv.setViewName("confirmbooking");
            mv.addObject("totalSeats", totalSeats);
            session.setAttribute("totalSeats",totalSeats);
            mv.addObject("selectedSeats", selectedSeats);
            session.setAttribute("selectedSeats",selectedSeats);
        }
        return mv;
    }

//    @GetMapping("/registersuccess")
//    public ModelAndView registerSuccess(HttpSession session) {
//        ModelAndView mv = new ModelAndView("bookingsuccess");
//        return mv;
//    }
    @GetMapping("signup")
    public ModelAndView signup() {
        ModelAndView mv = new ModelAndView("signup");
        return mv;
    }

    @PostMapping("/payment")
    public ModelAndView payment(String userName, String password) {
        ModelAndView mv = new ModelAndView("payment");
        userService.insertUser(new User(userName,password));
        return mv;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
