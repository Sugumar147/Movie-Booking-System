<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.demo.model.Seat" %>
<%@ page import="com.example.demo.model.Ticket" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Confirmation</title>
    <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f9f9f9;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .container {
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                padding: 40px;
                max-width: 400px;
                width: 100%;
                text-align: center;
            }
            h1 {
                color: #333;
                margin-bottom: 20px;
            }
            p {
                color: #666;
                margin-bottom: 20px;
            }
            .button-container {
                display: flex;
                justify-content: center;
                margin-bottom: 20px;
            }
            button {
                background-color: #007bff;
                color: #fff;
                padding: 10px 15px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
                margin-right: 10px; /* Add margin between buttons */
            }
            button:last-child {
                margin-right: 0; /* Remove margin from last button */
            }
            button:hover {
                background-color: #0056b3;
            }
            input[type="text"],
            input[type="password"],
            button.button-link {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
                font-size: 16px;
            }
            .button-link {
                background-color: transparent;
                color: #007bff;
                border: none;
                cursor: pointer;
                transition: color 0.3s ease;
            }
            .button-link:hover {
                color: #0056b3;
            }
        </style>
</head>
<body>
    <div class="container">
        <div class="button-container">
            <button onclick="location.href='/'">Home</button>
            <button onclick="location.href='/logout'">Logout</button>
        </div>
        <h1>Booking Confirmation</h1>

        <div class="logged-in-message">
            <p>Your booking has been confirmed.</p>
            <p>Thank you for booking with us!</p>
            <p>Booking ID: ${ticket.bookingId}</p>
            <p>Movie: ${ticket.movie}</p>
            <p>Theatre: ${ticket.theatre}</p>
            <p>Number of Seats: <%= session.getAttribute("totalSeats")%></p>
            <p>Seats: <%= session.getAttribute("selectedSeats")%></p>
            <p>Amount: ${ticket.amount}</p>
        </div>
    </div>
</body>
</html>

