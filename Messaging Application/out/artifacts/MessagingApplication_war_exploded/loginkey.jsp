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
        .button {
            width: 100%;
            height: 75px;
            clear: both;
        }
    </style>
</head>

<body>
<div class="container">
    <h2 name="approval">Request Approved</h2>
    <h3 name="id">Agent ID: ${id}</h3>
    <h3 name="name">Agent Name: ${name}</h3>
    <h3 name="loginkey">Login Key: ${loginkey}</h3>
    <form action="/loginkeypage" method="post">
        <input class="button" type="submit" value="Continue To Login" name="loginButton"><br /><br />
    </form>
</div>
</body>

</html>