<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Seat Selection</title>
    <style>
        /* General styles */
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
            display: flex;
            flex-direction: column;
            align-items: center; /* Center horizontally */
        }
        h1, h2 {
            color: #333;
            text-align: center;
        }
        .seats {
            margin-top: 20px;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 10px; /* Add gap between seats */
        }
        .seat {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 30px; /* Adjust size of seats */
            height: 30px;
            background-color: #eee;
            border-radius: 5px;
            cursor: pointer;
            position: relative;
            transition: background-color 0.3s ease;
        }
        .seat input[type="checkbox"] {
            display: none;
        }
        .seat.disabled {
            background-color: #A8A9AD;
            cursor: not-allowed;
        }
        .seat.selected {
            background-color: #D291BC; /* Change background color when selected */
        }
        .seat label {
            pointer-events: none; /* Prevent label from intercepting clicks */
            margin: 0; /* Remove default margin */
        }
        .image-container {
            max-width: 100%; /* Ensure the image container does not exceed the width of its parent */
            overflow: hidden; /* Hide any overflowing content */
            margin-bottom: 20px; /* Add margin to separate from seats */
        }
        .image-container img {
            width: 80%; /* Ensure the image fills the container */
            height: auto; /* Maintain aspect ratio */
            display: block; /* Remove any default image spacing */
            margin: 0 auto; /* Center horizontally */
        }
        .logout-button {
            position: absolute; /* Added */
            top: 20px; /* Added */
            right: 20px; /* Added */
            padding: 10px 20px;
            background-color: #0056b3; /* Changed color */
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .logout-button:hover {
            background-color: #ff0000;
        }
        .logout-form {
            display : flex;
            justify-content : flex-end;
            padding : 20px 40px;
        }
    </style>
</head>
<body>
    <div class="logout-form">
        <%
            String loggedInUser = (String) session.getAttribute("loggedInUser");
            if (loggedInUser != null ) {
        %>
            <form action="logout">
                <input type="submit" class="logout-button" value="Logout">
            </form>
        <% } %>
    </div>
    <div class="container">
        <h1>Seat Selection</h1>
        <h2>Selected Theatre: ${selectedTheatre}</h2>
        <div class="image-container">
            <img src="/images/screen-img2.png" alt="Theatre Image">
        </div>
        <div class="screen"></div>
        <form action="confirmbooking" method="post">
            <div class="seats">
                <c:forEach var="seat" items="${seatList}" varStatus="status">
                    <div class="seat ${occupiedSeats.contains(seat.getSeatNumber()) ? 'disabled' : ''}">
                        <input type="checkbox" id="${seat.seatNumber}" name="selectedSeats" value="${seat.seatNumber}" ${occupiedSeats.contains(seat.getSeatNumber()) ? 'disabled' : ''}>
                        <label for="${seat.seatNumber}">${seat.seatNumber}</label>
                    </div>
                    <c:if test="${status.index % 18 == 5}"></div><div class="seats"></c:if> <!-- Close and reopen .seats div every 5 seats -->
                </c:forEach>
            </div>
            <br><br>
            <div align="center">
              <button type="submit" >Book Selected Seats</button>
            </div>
        </form>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            const seats = document.querySelectorAll('.seat');

            seats.forEach(seat => {
                seat.addEventListener('click', function() {
                    if (!seat.classList.contains('disabled')) {
                        seat.classList.toggle('selected');
                        const checkbox = seat.querySelector('input[type="checkbox"]');
                        checkbox.checked = !checkbox.checked;
                    }
                });
            });
        });
    </script>
</body>
</html>
