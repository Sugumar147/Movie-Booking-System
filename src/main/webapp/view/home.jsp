<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.model.Movie" %>
<%@ page import="com.example.demo.Services.MovieService" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to the Ticket Booking System</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        /* Container styles */
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #f5f5f5;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        /* Heading styles */
        h1, h2 {
            text-align: center;
            color: #333;
        }

        /* Movie selection styles */
        .movies {
            display: flex;
            overflow-x: auto; /* Enable horizontal scrolling */
            margin-top: 20px;
        }

        .movie {
            width: 150px; /* Adjust width as needed */
            margin-right: 20px; /* Add spacing between movies */
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
        }

        .movie:last-child {
            margin-right: 0; /* Remove right margin for the last movie */
        }

        .movie:hover {
            transform: translateY(-5px);
            border: 1px solid #000000;
        }

        .movie img {
            width: 100%;
            height: auto;
            border-radius: 10px 10px 0 0;
            display: block;
            margin: 0 auto;
        }

        .movie p {
            margin-top: 10px;
            text-align: center;
            font-weight: bold;
        }

        /* Selected movie styles */
        .movie.selected {
            border: 1px solid #000001;
            background-color: #ffcccc; /* Add background color to indicate selection */
        }

        /* Link styles */
        .movie a {
            text-decoration: none;
            color: inherit;
            display: block;
            text-align: center;
        }

        /* Link hover styles */
        .movie a:hover {
            color: #007bff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to the Ticket Booking System</h1>
        <h2>Select a Movie</h2>
        <div class="movies">
            <%
                // Retrieve list of movies
                List<Movie> movies = new MovieService().getMovies();
                // Iterate over movies
                for (Movie movie : movies) {
            %>
            <div class="movie">
                <a href="theatrelist?movie=<%= movie.getName() %>">
                    <img src="<%= movie.getImagePath() %>" alt="<%= movie.getName() %>">
                    <p><%= movie.getName() %></p>
                </a>
            </div>
            <% } %>
        </div>
    </div>
</body>
</html>
