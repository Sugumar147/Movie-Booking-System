<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Seat Selection</title>
    <style>
        /* Add your CSS styles here */
        /* Example: */
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
        .seats {
            margin-top: 20px;
            display: grid;
            grid-template-columns: repeat(5, 1fr); /* Adjust to 5 seats per row */
            gap: 10px;
            justify-content: center; /* Center the seats horizontally */
        }
        .screen {
            background-color: blue;
            color: white;
            text-align: center;
            padding: 10px;
        }
        /* Add more styles as needed */
    </style>
</head>
<body>
    <div class="container">
        <h1>Seat Selection</h1>
        <h2>Selected Theatre: ${selectedTheatre}</h2>
        <div class="screen"></div>
        <form action="confirmbooking" method="post">
            <div class="seats" align="center">
                <c:forEach var="seat" items="${seatList}">
                    <label>
                        <input type="checkbox" name="selectedSeats" value="${seat.seatNumber}">
                        ${seat.seatNumber} <!-- You can add more details if needed -->
                    </label>
                </c:forEach>
            </div>
            <br><br>
            <div align="center">
              <button type="submit" >Book Selected Seats</button>
            </div>
        </form>
    </div>
</body>
</html>
