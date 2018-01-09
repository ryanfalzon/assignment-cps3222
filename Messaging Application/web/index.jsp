<%--
  Created by IntelliJ IDEA.
  User: Ryan Falzon
  Date: 30-Dec-17
  Time: 10:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Home Page</title>
    <style type="text/css">
        .container {
            width: 500px;
            clear: both;
            text-align: center;
            align-self: center;
        }
        .container input {
            width: 250px;
            height: 50px;
            clear: both;
        }
    </style>
</head>

<body>
<div class="container">
    <h2>Home Page</h2>
    <form action="/contactsupervisor.jsp" method="post">
        <input type="submit" value="Contact Supervisor" name="contactButton"><br /><br />
    </form>
</div>
</body>

</html>
