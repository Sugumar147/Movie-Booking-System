package com.example.demo.controller;

import com.example.demo.Services.*;
import com.example.demo.mapper.BookingMapper;
import com.example.demo.mapper.UsersMapper;
import com.example.demo.model.*;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.ibatis.type.MappedTypes;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
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
    private BookingMapper bookingMapper;
    @Autowired
    private MovieService movieService;
    @Autowired
    private TheatreService theatreService;
    @Autowired
    private TicketService ticketService;
    @GetMapping("/theatrelist")
    public ModelAndView theatrelist(String movie  , HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if(movie==null) {
            mv.setViewName("redirect:/");
            return mv;
        }
        session.setAttribute("movieName" , movie);
        mv.setViewName("theatrelist");
        return mv;
    }
    @GetMapping("/booking")
    public ModelAndView booking(@Nullable @RequestParam("theatreName") String selectedTheatre ,String timing,  HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if(selectedTheatre==null) {
            mv.setViewName("redirect:/");
            return mv;
        }
        Theatre theatre = TheatreService.getTheatreByName(selectedTheatre);
        List<String> occupiedSeats = bookingService.getOccupiedSeats(selectedTheatre,timing);
        System.out.println(occupiedSeats);
        List<Seat> seatList = theatre.GetSeatList();
        mv.setViewName("booking");
        mv.addObject("occupiedSeats",occupiedSeats);
        mv.addObject("seatList", seatList);
        session.setAttribute("theatre",theatre );
        session.setAttribute("timing",timing);
        mv.addObject("selectedTheatre", selectedTheatre);
        session.setAttribute("selectedTheatre", selectedTheatre);
        return mv;
    }
    @RequestMapping("/login")
    public ModelAndView login(String username,String password,  RedirectAttributes redirectAttributes,HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if(userService.verifyLogin(username,password)) {
            mv.setViewName("payment");
            mv.addObject("userName",username);
            mv.addObject("password",password);
            session.setAttribute("userName",username);
            session.setAttribute("password",password);
            session.setAttribute("loggedInUser",username);
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
            List<String> selectedSeats = (List<String>) session.getAttribute("selectedSeats");
            String seats = String.join(", ", selectedSeats);
            mv.setViewName("redirect:/confirmbooking?selectedSeats="+seats);
        }
        return mv;
    }

    @RequestMapping("/bookingsuccess")
    public ModelAndView bookingSuccess(HttpSession session)  throws InterruptedException{
        ModelAndView mv = new ModelAndView();
        String userName = (String) session.getAttribute("userName");
        String password = (String) session.getAttribute("password");
        if(userName == null && password == null) {
            mv.setViewName("redirect:/");
            return mv;
        }
        mv.setViewName("bookingsuccess");
        Ticket ticket = ticketService.generateTicket();
        mv.addObject("ticket",ticket);
        session.setAttribute("ticket",ticket);
        Theatre theatre = (Theatre) session.getAttribute("theatre");
        String timing = (String) session.getAttribute("timing");
        List<String> selectedSeats = (List<String>) session.getAttribute("selectedSeats");
        theatreService.occupySeats(theatre,selectedSeats,timing,ticket,userName);
        return mv;
    }

    @RequestMapping("/secondarylogin")
    public ModelAndView secondarylogin(String username,String password,HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if(username!=null && password!=null && userService.verifyLogin(username,password)) {
            List<Ticket> tickets = bookingMapper.getAllBookingsByUserName(username);
            mv.addObject("tickets",tickets);
            mv.setViewName("mybookings");
            mv.addObject("userName",username);
            mv.addObject("password",password);
            session.setAttribute("userName",username);
            session.setAttribute("password",password);
            session.setAttribute("loggedInUser",username);
        }
        else {
            mv.setViewName("login");
        }
        return mv;
    }

    @RequestMapping("/confirmbooking")
    public ModelAndView confirmBooking(@Nullable @RequestParam("selectedSeats") List<String> selectedSeats,HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Theatre theatre = (Theatre) session.getAttribute("theatre");
        if(selectedSeats==null) {
            mv.setViewName("redirect:/");
            return mv;
        }
        String selectedTheatre = (String) session.getAttribute("selectedTheatre");
        String timing = (String) session.getAttribute("timing");
        if(selectedSeats==null) {
            mv.setViewName("redirect:/booking?theatreName=" + selectedTheatre  + "&timing=" + timing);
        }
        else {
            int totalSeats = selectedSeats.size();
            mv.addObject("totalSeats", totalSeats);
            session.setAttribute("totalSeats",totalSeats);
            mv.addObject("selectedSeats", selectedSeats);
            session.setAttribute("selectedSeats",selectedSeats);
            String loggedInUser = (String) session.getAttribute("loggedInUser");
            if (loggedInUser != null && !loggedInUser.isEmpty()) {
                mv.setViewName("payment");
            } else {
                mv.setViewName("confirmbooking");
            }
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

    @GetMapping("/mybookings")
    public ModelAndView myBookings(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String userName = (String) session.getAttribute("userName");
        if(userName==null) {
            mv.setViewName("login");
            return mv;
        }
        List<Ticket> tickets = bookingMapper.getAllBookingsByUserName(userName);
        mv.addObject("tickets",tickets);
        mv.setViewName("mybookings");
        return mv;
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if(session!=null) {
            session.removeAttribute("loggedInUser");
            session.invalidate();
        }
        mv.setViewName("redirect:/loggedout.html");
        return mv;
    }
    @RequestMapping("/payment")
    public ModelAndView payment(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        List<Seat> seatList = (List<Seat>) session.getAttribute("selectedSeats");
        if(seatList==null) {
            mv.setViewName("redirect:/mybookings");
            session.setAttribute("userName",user.getUserName());
            session.setAttribute("password",user.getPassword());
            userService.insertUser(user);
            session.setAttribute("user", user);
            session.setAttribute("loggedInUser", user.getUserName());
            return mv;
        }
        mv.setViewName("payment");
        session.setAttribute("userName",user.getUserName());
        session.setAttribute("password",user.getPassword());
        userService.insertUser(user);
        session.setAttribute("user", user);
        session.setAttribute("loggedInUser", user.getUserName());
        return mv;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
