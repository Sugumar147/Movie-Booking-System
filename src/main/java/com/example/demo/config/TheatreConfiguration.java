package com.example.demo.config;

import com.example.demo.Services.TheatreService;
import com.example.demo.model.Theatre;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = {"com.example.demo"})
public class TheatreConfiguration {
    @Bean
    public Theatre inox() {
        List<String> moviesList1 = Arrays.asList("Dune","The Batman");
        Map<String, List<String>> movieTimingsMap = new HashMap<>();
        movieTimingsMap.put("Dune", Arrays.asList("09:00 AM", "08:00 PM"));
        movieTimingsMap.put("The Batman", Arrays.asList("01:00 PM"));
        Theatre theatre = new Theatre(1, "Inox Cinemas", "10", moviesList1,movieTimingsMap);
        TheatreService.addTheatre(theatre);
        return theatre;
    }

    @Bean
    public Theatre karpagam() {
        List<String> moviesList2 = Arrays.asList("Deadpool","Dune");
        Map<String, List<String>> movieTimingsMap = new HashMap<>();
        movieTimingsMap.put("Deadpool", Arrays.asList("10:00 AM","07:00 PM"));
        movieTimingsMap.put("Dune", Arrays.asList("03:00 PM","10:00 PM"));
        Theatre theatre = new Theatre(2, "Karpagam Cinemas", "15", moviesList2,movieTimingsMap);
        TheatreService.addTheatre(theatre);
        return theatre;
    }

    @Bean
    public Theatre thangam() {
        List<String> moviesList3 = Arrays.asList("The Batman");
        Map<String, List<String>> movieTimingsMap = new HashMap<>();
        movieTimingsMap.put("The Batman", Arrays.asList("01:00 PM","07:00 PM"));
        Theatre theatre = new Theatre(3, "Thangam Cinemas", "8", moviesList3,movieTimingsMap);
        TheatreService.addTheatre(theatre);
        return theatre;
    }

    @Bean
    public Theatre pvr() {
        List<String> moviesList4 = Arrays.asList("Dune","Fight Club","Deadpool");
        Map<String, List<String>> movieTimingsMap = new HashMap<>();
        movieTimingsMap.put("Dune", Arrays.asList("06:00 PM","09:00 PM"));
        movieTimingsMap.put("Fight Club", Arrays.asList("08:00 AM", "11:00 AM"));
        movieTimingsMap.put("Deadpool", Arrays.asList("11:30 AM","03:00 PM"));
        Theatre theatre = new Theatre(4, "PVR Cinemas", "17", moviesList4,movieTimingsMap);
        TheatreService.addTheatre(theatre);
        return theatre;
    }
}
