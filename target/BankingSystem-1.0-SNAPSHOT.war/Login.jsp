<%--
  Created by IntelliJ IDEA.
  User: mr bones
  Date: 11/19/2021
  Time: 12:40 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

</head>
<body>
<h1><%= "Login here" %>
</h1>
<br/>
<form action="Login" method="post">
    Enter name: <input type="text" name="username"><br>
    Enter pass: <input type="password" name="pass"><br>
    <input type="submit" value="Login">
</form>


</body>
</html>
