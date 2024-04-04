<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.demo.Services.MovieService" %>
<%@ page import="com.example.demo.Services.TheatreService" %>
<%@ page import="com.example.demo.model.Theatre" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Theatre Selection</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        h1, h2 {
            color: #333;
            text-align: center;
        }
        .theatres {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-top: 30px;
        }
        .theatre {
            background-color: #f9f9f9;
            border-radius: 5px;
            padding: 15px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease;
            cursor: pointer;
            color: #555;
            text-align: center;
            position: relative;
            overflow: hidden;
        }
        .theatre:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }
        .theatre p {
            margin: 0;
            font-size: 18px;
        }
        .theatre-details {
            margin-top: 10px;
            font-size: 14px;
            color: #799;
        }
        .movie-timings {
            list-style-type: none;
            padding: 0;
            text-align: center;
            margin-top: 10px;
            font-size: 20px; /* Larger font size for timings */
        }
        .movie-timings li {
            display: inline-block; /* Display timings horizontally */
            margin-right: 10px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .movie-timings li:hover {
            background-color: green;
            color: #333; /* Change color on hover */
            border-radius: 3px; /* Add border-radius on hover */
            padding: 5px 10px; /* Adjust padding on hover */
        }
/*           .theatre a {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            pointer-events: none;
        }  */
        .theatre a:hover {
            text-decoration: none;
        }
        .no-result {
          font-family: Arial, sans-serif;
          font-size: 18px;
          color: #555;
          text-align: center;
          margin-top: 50px;
        }

    </style>
</head>
<body>
    <div class="container">
        <h1>Available Theatres</h1>
        <h2>Showing Results For:
            <%
                String selectedMovie = request.getParameter("movie");
                out.print(selectedMovie);
            %>
        </h2>
        <div class="theatres">
            <%
                List<Theatre> theatreList = new MovieService().getTheatreList(selectedMovie);
                Map<String, Theatre> theatreMap = new HashMap<>();
                for (Theatre theatre : theatreList) {
                    theatreMap.put(theatre.getName(), theatre);
                }
                new TheatreService().addSeatsForTheatre(theatreMap.values());
                for (Theatre theatre : theatreList) {
                    List<String> movieTiming = theatre.getMovieTimingsMap().get(selectedMovie);
                    if(new MovieService().isAvailableNow(movieTiming)) {
            %>
            <div class="theatre">
                <p><%= theatre.getName() %></p>
                <div class="theatre-details">
                    <p>Seat Capacity: <%= theatre.getSeatCapacity() %></p>
                    <p>Seats Available: <%= theatre.GetSeatList().size() %></p>
                    <p>Movie Timings:</p>
                    <ul class="movie-timings">
                        <%
                            List<String> movieTimings = theatre.getMovieTimingsMap().get(selectedMovie);
                            if (movieTimings != null) {
                                for (String timing : movieTimings) {
                                    if(new MovieService().isAvailableNow(timing)) {
                        %>
                        <li><a href="booking?theatreName=<%= theatre.getName() %>&timing=<%= timing %>"><%= timing %></a></li>
                        <%
                                    }
                                }
                            }
                        %>
                    </ul>
                </div>
                <a href="booking?theatreName=<%= theatre.getName() %>"></a>
            </div>
            <% }
                else {
             %>
               <div class="theatre">
                    <p><%= theatre.getName() %></p>
                      <div class="theatre-details">
                            <p>No shows available</p>
                      </div>
                </div>
             <% } %>
            <% } %>
        </div>
    </div>

</body>
</html>
