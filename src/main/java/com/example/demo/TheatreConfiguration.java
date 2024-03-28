package com.example.demo;

import com.example.demo.Services.TheatreService;
import com.example.demo.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan(basePackages = {"com.example.demo"})
public class TheatreConfiguration {
    @Bean
    public Theatre inox() {
        List<String> moviesList1 = Arrays.asList("Dune","The Batman");
        Theatre theatre = new Theatre(1, "Inox Cinemas", "10", moviesList1);
        TheatreService.addTheatre(theatre);
        return theatre;
    }

    @Bean
    public Theatre karpagam() {
        List<String> moviesList2 = Arrays.asList("Deadpool","Dune");
        Theatre theatre = new Theatre(2, "Karpagam Cinemas", "15", moviesList2);
        TheatreService.addTheatre(theatre);
        return theatre;
    }

    @Bean
    public Theatre thangam() {
        List<String> moviesList3 = Arrays.asList("The Batman");
        Theatre theatre = new Theatre(3, "Thangam Cinemas", "8", moviesList3);
        TheatreService.addTheatre(theatre);
        return theatre;
    }

    @Bean
    public Theatre pvr() {
        List<String> moviesList4 = Arrays.asList("Dune","Fight Club","Deadpool");
        Theatre theatre = new Theatre(4, "PVR Cinemas", "17", moviesList4);
        TheatreService.addTheatre(theatre);
        return theatre;
    }
}
