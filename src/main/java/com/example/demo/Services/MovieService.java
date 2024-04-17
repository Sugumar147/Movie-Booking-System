package com.example.demo.Services;


import com.example.demo.DemoApplication;
import com.example.demo.config.TheatreConfiguration;
import com.example.demo.model.Movie;
import com.example.demo.model.Theatre;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    TheatreConfiguration theatreConfiguration;


    public List<Movie> getMovies() {
        List<Movie> list = new ArrayList<>();
        list.add(new Movie(1, "Dune","/images/dune.jpg"));
        list.add(new Movie(2, "The Batman","/images/batman.jpg"));
        list.add(new Movie(3, "Fight Club","/images/fight-club.jpg"));
        list.add(new Movie(4, "Deadpool","/images/deadpool.webp"));
        return list;
    }

    public Boolean isAvailableNow(String timing) {
        LocalTime currentTime = LocalTime.now();
        String amOrPm = timing.substring(6);
        int hours,minutes;
        if(amOrPm.equals("AM")) {
            hours = Integer.parseInt(timing.substring(0,2));
        } else {
            hours = (Integer.parseInt(timing.substring(0,2)))+12;
        }
        minutes = Integer.parseInt(timing.substring(3,5));
        LocalTime targetTime = LocalTime.of(hours, minutes);
        return currentTime.isBefore(targetTime);
    }

    public Boolean isAvailableNow(List<String> timing) {
        int count=0;
        for (String time : timing) {
            if (!isAvailableNow(time)) {
                count++;
            }
        }
        if(timing.size()==count) {
            return false;
        }
        return true;
    }

    public List<Theatre> getTheatreList(String movieName) {
        List<Theatre> theatreList = new ArrayList<>();
        var context = DemoApplication.context;

        Theatre theatre1 = context.getBean(TheatreConfiguration.class).inox();
        Theatre theatre2 = context.getBean(TheatreConfiguration.class).karpagam();
        Theatre theatre3 = context.getBean(TheatreConfiguration.class).thangam();
        Theatre theatre4 = context.getBean(TheatreConfiguration.class).pvr();

        // Check if each theatre is running the movie
        if (theatre1.getMoviesList().contains(movieName)) {
            theatreList.add(theatre1);
        }
        if (theatre2.getMoviesList().contains(movieName)) {
            theatreList.add(theatre2);
        }
        if (theatre3.getMoviesList().contains(movieName)) {
            theatreList.add(theatre3);
        }
        if (theatre4.getMoviesList().contains(movieName)) {
            theatreList.add(theatre4);
        }

        return theatreList;
    }

}
