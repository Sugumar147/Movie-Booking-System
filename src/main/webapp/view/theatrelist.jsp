<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.Services.MovieService" %>
<%@ page import="com.example.demo.Theatre" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            margin-top: 50px;
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
            cursor: pointer; /* Add cursor pointer to indicate clickable */
        }
        .theatre:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }
        .theatre p {
            margin: 0;
            font-size: 18px;
            color: #555;
        }
        .theatre-details {
            margin-top: 10px;
            font-size: 14px;
            color: #777;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Available Theatres</h1>
        <h2>Showing Results For:
            <%
                // Retrieve selected movie from previous page
                String selectedMovie = request.getParameter("movie");
                // Display selected movie
                out.print(selectedMovie);
            %>
        </h2>
        <div class="theatres">
            <%
                // Retrieve theatre list for the selected movie
                List<Theatre> theatreList = new MovieService().getTheatreList(selectedMovie);
                // Iterate over theatre list
                for (Theatre theatre : theatreList) {
            %>
                <!-- Wrap each theatre with an anchor tag linking to the booking page -->
                <a href="booking?theatreName=<%= theatre.getName() %>">
                    <div class="theatre">
                        <p align="center"><%= theatre.getName() %></p>
                        <div class="theatre-details">
                            <p align="center">Seat Capacity: <%= theatre.getSeatCapacity() %></p>
                        </div>
                    </div>
                </a>
            <% } %>
        </div>
    </div>
</body>
</html>
