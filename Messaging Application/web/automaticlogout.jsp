<%--
  Created by IntelliJ IDEA.
  User: Ryan Falzon
  Date: 04-Jan-18
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Automatic Logout</title>
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
    <h2 name="approval">Automatic Logout</h2>
    <h3 name="id">You have sent more than 25 messages and you will be logged out.</h3>
    <h3 name="name">Press ok to continue...</h3>
    <form action="/index.jsp" method="post">
        <input type="submit" value="Ok" name="logoutButton"><br /><br />
    </form>
</div>
</body>

</html>
