<%--
  Created by IntelliJ IDEA.
  User: Ryan Falzon
  Date: 02-Jan-18
  Time: 9:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Login Key Request</title>
    <style type="text/css">
        .container {
            width: 500px;
            clear: both;
            text-align: center;
        }
        .container input {
            width: 75%;
            clear: both;
        }
    </style>
</head>

<body>
<div class="container">
    <h2 name="approval">${approval}</h2>
    <h3 name="id">Agent ID: ${id}</h3>
    <h3 name="name">Agent Name: ${name}</h3>
    <h3 name="loginkey">Login Key: ${loginkey}</h3>
    <form action="/loginkeypage" method="post">
        <input type="submit" value="Continue To Login" name="loginButton"><br /><br />
        <input type="submit" value="Back To Main Menu" name="backButton"><br /><br />
    </form>
</div>
</body>

</html>