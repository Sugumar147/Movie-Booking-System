package com.example.demo.Services;


import com.example.demo.DemoApplication;
import com.example.demo.TheatreConfiguration;
import com.example.demo.Movie;
import com.example.demo.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    TheatreConfiguration theatreConfiguration;


    public List<Movie> getMovies() {
        List<Movie> list = new ArrayList<>();
        list.add(new Movie(1, "Dune","https://shorturl.at/hAJLR"));
        list.add(new Movie(2, "The Batman","https://shorturl.at/fJQ25"));
        list.add(new Movie(3, "Fight Club","https://shorturl.at/vALQW"));
        list.add(new Movie(4, "Deadpool","https://shorturl.at/BMUW8"));
        return list;
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
