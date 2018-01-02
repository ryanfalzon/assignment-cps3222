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
    <h2>${approval}</h2>
    <h3>Agent ID: ${id}</h3>
    <h3>Agent Name: ${name}</h3>
    <h3>Login Key: ${loginkey}</h3>
    <form action="/index.jsp" method="post">
        <input type="submit" value="Back To Main Menu"><br /><br />
    </form>
</div>
</body>

</html>