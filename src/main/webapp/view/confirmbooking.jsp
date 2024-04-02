<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
        a {
            color: #007bff;
            text-decoration: none;
            transition: color 0.3s ease;
        }
        a:hover {
            color: #0056b3;
        }
        input[type="text"],
        input[type="password"],
        button {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 16px;
        }
        button {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Booking Confirmation</h1>

        <%
            // Check if user is logged in
            boolean isLoggedIn = false; // Assuming user is not logged in initially
            // You need to implement logic to check if the user is logged in or not.
            // For demonstration, I'm assuming isLoggedIn is set to true if user is logged in.
            // You may need to replace this with your actual logic.

            if (isLoggedIn) {
        %>
                <div class="logged-in-message">
                    <p>Your booking has been confirmed.</p>
                    <p>Thank you for booking with us!</p>
                </div>
        <%
            } else {
        %>
                <div class="logged-out-message">
                    <p>You need to be logged in to confirm your booking.</p>
                    <p>Please enter your credentials:</p>
                    <spring:hasBindErrors name="error">
                        <div class="alert alert-danger">
                            ${error}
                        </div>
                    </spring:hasBindErrors>
                    <form class="login-form" action="/login" method="POST">
                        <input type="text" name="username" placeholder="Username" required><br>
                        <input type="password" name="password" placeholder="Password" required><br>
                        <button type="submit">Login</button>
                    </form>
                    <p>Don't have an account? <a href="signup">Register</a></p>
                </div>
        <%
            }
        %>
    </div>
</body>
</html>
