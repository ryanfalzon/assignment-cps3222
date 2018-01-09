<%--
  Created by IntelliJ IDEA.
  User: Ryan Falzon
  Date: 30-Dec-17
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Login Page</title>
    <style type="text/css">
        .container {
            width: 500px;
            clear: both;
            text-align: center;
        }
        .container input {
            width: 100%;
            clear: both;
        }
        .container p{
            color: red;
        }
    </style>
</head>

<body>
<div class="container">
    <h2>Login Page</h2>
    <h3>Please enter the following details to be logged in to the system.</h3>
    <form action="/login" method="post">
        <label>ID</label>
        <input type="text" name="id"><br />
        <label>Login Key</label>
        <input type="text" name="loginkey"><br />
        <p name="error">${errorlogin}</p>
        <input type="submit" value="Login" name="loginbutton"><br /><br />
        <input type="submit" value="Back To Main Menu" name="backButton">
    </form>
</div>
</body>

</html>