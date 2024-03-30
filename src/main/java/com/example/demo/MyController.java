package com.example.demo;

import com.example.demo.mapper.UsersMapper;
import com.example.demo.model.User;
import com.example.demo.Services.MovieService;
import com.example.demo.Services.TheatreService;
import com.example.demo.Services.UserService;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@MappedTypes(User.class)
@MapperScan("com.example.demo.mapper")
@Controller
public class MyController {
    @Autowired
    private UserService userService;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private MovieService movieService;
    @Autowired
    private TheatreService theatreService;
    @GetMapping("/theatrelist")
    public ModelAndView theatrelist() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("theatrelist");
        return mv;
    }
    @GetMapping("/booking")
    public ModelAndView booking(@RequestParam("theatreName") String selectedTheatre) {
        Theatre theatre = TheatreService.getTheatreByName(selectedTheatre);
        theatreService.addSeatsForTheatre(theatre);
        List<Seat> seatList = theatre.GetSeatList();
        // Add theatre list and selected theatre to the model
        ModelAndView mv = new ModelAndView("booking");
        mv.addObject("seatList", seatList);
        mv.addObject("selectedTheatre", selectedTheatre);
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
    public ModelAndView bookingSuccess()  throws InterruptedException{
        ModelAndView mv = new ModelAndView("bookingsuccess");
//        Thread.sleep(3000);
        return mv;
    }
    @RequestMapping("/confirmbooking")
    public ModelAndView confirmBooking() {
        ModelAndView mv = new ModelAndView("confirmbooking");
        return mv;
    }

    @PostMapping("/registersuccess")
    public ModelAndView registerSuccess() {
        ModelAndView mv = new ModelAndView("bookingsuccess");
        return mv;
    }
    @GetMapping("signup")
    public ModelAndView signup() {
        ModelAndView mv = new ModelAndView("signup");
        return mv;
    }

    @PostMapping("/payment")
    public ModelAndView payment() {
        ModelAndView mv = new ModelAndView("payment");
        return mv;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
