<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Bookings</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #f5f5f5;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1, h2 {
            text-align: center;
            color: #333;
        }
        .booking {
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #fff;
            margin-bottom: 20px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .booking-details {
            margin-bottom: 10px;
        }
        .booking-label {
            font-weight: bold;
            color: #555;
            margin-right: 10px;
        }
        .booking-value {
            color: #333;
        }
        .logout-button {
            position: absolute;
            top: 20px;
            right: 20px;
            padding: 10px 20px;
            background-color: #0056b3;
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

        /* My Bookings button styles */
        .mybookings-button {
            position: absolute;
            top: 20px;
            left: 20px; /* Adjusted to be on the opposite direction */
            padding: 10px 20px;
            background-color: #0056b3;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .mybookings-button:hover {
            background-color: #ff0000;
        }
    </style>
</head>
<body>
    <div class="logout-form">
        <%
            String loggedInUser = (String) session.getAttribute("loggedInUser");
            if (loggedInUser != null) {
        %>
                <!-- Render logout button -->
                <form action="logout">
                    <input type="submit" class="logout-button" value="Logout">
                </form>
        <% } %>
    </div>
    <div class="mybookings">
        <form action="/">
            <input type="submit" class="mybookings-button" value="Home">
        </form>
    </div>
    <div class="container"\>
        <h1>My Bookings</h1>
        <c:forEach var="ticket" items="${tickets}">
            <div class="booking">
                <div class="booking-details">
                    <span class="booking-label">Ticket ID:</span>
                    <span class="booking-value">${ticket.getBookingId()}</span>
                </div>
                <div class="booking-details">
                    <span class="booking-label">Movie Title:</span>
                    <span class="booking-value">${ticket.movie}</span>
                </div>
                <div class="booking-details">
                    <span class="booking-label">Theatre Name:</span>
                    <span class="booking-value">${ticket.theatre}</span>
                </div>
                <div class="booking-details">
                    <span class="booking-label">Movie Timing:</span>
                    <span class="booking-value">${ticket.timing}</span>
                </div>
                <div class="booking-details">
                    <span class="booking-label">Seat Number(s):</span>
                    <c:forEach var="seat" items="${ticket.seatNumbers}">
                        <span class="booking-value">${seat}</span>
                    </c:forEach>
                </div>
                <div class="booking-details">
                    <span class="booking-label">Total Amount:</span>
                    <span class="booking-value">${ticket.amount}</span>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
