<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
        }
        h2 {
            color: #333;
            text-align: center;
        }
        .payment-form {
            display: flex;
            flex-direction: column;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 5px;
            color: #666;
        }
        .form-group input[type="text"],
        .form-group input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            box-sizing: border-box;
        }
        .form-group input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .form-group input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .booking-details {
            margin-top: 20px;
            font-size: 16px;
            color: #333;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Payment Details</h2>
        <%-- Display booking details --%>
         <div class="booking-details">
              <p>Selected Seats:<%= session.getAttribute("selectedSeats")%></p>
              <p>Number of Seats:<%= session.getAttribute("totalSeats")%></p>
              <p>Total Amount: ${totalSeats} x 200 = ${totalSeats * 200}</p>
        </div>
        <form class="payment-form" action="processing.html">


            <div class="form-group">
                <input type="submit" value="Pay  ₹${totalSeats * 200}">
            </div>
        </form>


    </div>
</body>
</html>
