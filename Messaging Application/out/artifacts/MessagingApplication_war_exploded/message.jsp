<%--
  Created by IntelliJ IDEA.
  User: Ryan Falzon
  Date: 30-Dec-17
  Time: 5:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Messaging Page</title>
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
        .error{
            color: red;
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
    <h2>Messaging Page</h2>
    <h3>Enter a message below and press send.</h3>
    <form action="/message" method="post">
        <label>Target Agent ID</label>
        <input type="text" name="targetagent"><br /><br />
        <textarea rows="4" cols="50" name="message"></textarea><br />
        <p name="error" class="error">${error}</p>
        <input class="button" type="submit" value="Ok" name="errorButton" hidden>
        <input class="button" type="submit" value="Submit Message" name="submitmessage">
    </form>
    <form action="/mailbox" method="post">
        <input class="button" type="submit" value="Check If You Have Messages" name="count">
        <p name="checkCount">${hasmessages}</p>
        <input class="button" type="submit" value="Get Next Message" name="next">
        <h4>Current Message</h4>
        <p name="newMessage">${message}</p>
    </form>
    <form action="/logout" method="post">
        <input class="button" type="submit" value="Logout" name="logoutButton"><br /><br />
    </form>
</div>
</body>

</html>