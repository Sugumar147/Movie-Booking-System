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
</head>
<body>
    <form:form action="/payment" method="post" modelAttribute="user"  >
        <label for="userName">Username:</label>
        <input type="text" id="userName" name="userName">
        <form:errors path="userName" cssClass="error-message"/><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password">
        <form:errors path="password" cssClass="error-message"/><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email">
        <form:errors path="email" cssClass="error-message"/><br>

        <div class="phone-input-group">
            <label for="countryCode">Country Code: </label>
            <input type="text" id="countryCode" name="countryCode">
            <form:errors path="countryCode" cssClass="error-message"/>

            <label for="phoneNumber">Phone Number:</label>
            <input type="tel" id="phoneNumber" name="phoneNumber">
            <form:errors path="phoneNumber" cssClass="error-message"/>
        </div>

        <input type="submit" value="Register">
    </form:form>

</body>
</html>
