<%--
  Created by IntelliJ IDEA.
  User: Ryan Falzon
  Date: 10-Jan-18
  Time: 11:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request Not Approved</title>
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
    <h2 name="approval">Request Not Approved</h2>
    <form action="/contactsupervisor.jsp" method="post">
        <input class="button" type="submit" value="Retry Contacting Supervisor" name="backButton"><br /><br />
    </form>
</div>
</body>
</html>
