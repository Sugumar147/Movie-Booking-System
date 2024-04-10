<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* Add your custom styles here */
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        form {
            max-width: 400px;
            margin: 0 auto;
            padding: 45px ;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            animation: fadeInUp 0.5s ease forwards;
        }
        input[type="text"],
        input[type="password"],
        input[type="email"],
        input[type="tel"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
            animation: slideInLeft 0.5s ease forwards;
        }
        .phone-input-group {
            display: flex;
            justify-content: space-between;
            animation: slideInRight 0.5s ease forwards;
        }
        .phone-input-group input[type="text"] {
            width: 25%; /* Adjusted width */
            margin-right: 10px; /* Added margin */
        }
        .phone-input-group input[type="tel"] {
            width: 65%; /* Adjusted width */
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 20px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            animation: slideInUp 0.5s ease forwards;
            margin: 0 auto; /* Center the button */
            display: block; /* Make it a block element to apply margin */
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(50px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes slideInLeft {
            from {
                opacity: 0;
                transform: translateX(-50px);
            }
            to {
                opacity: 1;
                transform: translateX(0);
            }
        }

        @keyframes slideInRight {
            from {
                opacity: 0;
                transform: translateX(50px);
            }
            to {
                opacity: 1;
                transform: translateX(0);
            }
        }

        @keyframes slideInUp {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
        .error-message {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }
    </style>
    <script>
            function validateForm() {
                var userName = document.getElementById("userName").value;
                var password = document.getElementById("password").value;
                var email = document.getElementById("email").value;
                var countryCode = document.getElementById("countryCode").value;
                var phoneNumber = document.getElementById("phoneNumber").value;

                var userNameError = document.getElementById("userNameError");
                var passwordError = document.getElementById("passwordError");
                var emailError = document.getElementById("emailError");
                var countryCodeError = document.getElementById("countryCodeError");
                var phoneNumberError = document.getElementById("phoneNumberError");

                // Clear previous error messages
                userNameError.innerHTML = "";
                passwordError.innerHTML = "";
                emailError.innerHTML = "";
                countryCodeError.innerHTML = "";
                phoneNumberError.innerHTML = "";

                var isValid = true;

                // Username validation
                if (userName.length < 3 || userName.length > 16) {
                    userNameError.innerHTML = "Username should be between 3 and 16 characters";
                    isValid = false;
                }

                // Password validation (can add more rules as needed)
                if (password.length === 0) {
                    passwordError.innerHTML = "Password is required";
                    isValid = false;
                }

                // Email validation
                if (!email.match(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/)) {
                    emailError.innerHTML = "Please enter a valid email address";
                    isValid = false;
                }

                // Other fields validation (e.g., not blank)
                if (countryCode.length === 0) {
                    countryCodeError.innerHTML = "Country code is required";
                    isValid = false;
                }

                if (phoneNumber.length === 0) {
                    phoneNumberError.innerHTML = "Phone number is required";
                    isValid = false;
                }

                return isValid;
            }
        </script>
</head>
<body>
    <form action="/payment" method="post" onsubmit="return validateForm()">
            <label for="userName">Username:</label>
            <input type="text" id="userName" name="userName">
            <div id="userNameError" class="error-message"></div><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password">
            <div id="passwordError" class="error-message"></div><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email">
            <div id="emailError" class="error-message"></div><br>

            <div class="phone-input-group">
                <label for="countryCode">Country Code: </label>
                <input type="text" id="countryCode" name="countryCode">
                <div id="countryCodeError" class="error-message"></div>

                <label for="phoneNumber">Phone Number:</label>
                <input type="tel" id="phoneNumber" name="phoneNumber">
                <div id="phoneNumberError" class="error-message"></div>
            </div>
            <input type="submit" value="Register">
    </form>
</body>
</html>