<%--
  Created by IntelliJ IDEA.
  User: Ryan Falzon
  Date: 30-Dec-17
  Time: 11:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Contact Supervisor</title>
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
    </style>
</head>

<body>
    <div class="container">
        <h2>Your Supervisor is John Doe</h2>
        <h3>Please enter the following details to be given a login key.</h3>
        <form action="LoginCheck.jsp" method="post">
            <label>ID</label>
            <input type="text" name="id"><br />
            <label>Name</label>
            <input type="text" name="name"><br /><br />
            <input type="submit" value="Get Login Key">
        </form>
    </div>
</body>

</html>
