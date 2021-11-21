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
</head>
<body>
<h1><%= "navigation" %>
</h1>
<br/>
<form action="Login" method="post">
    Enter name: <input type="text" name="username"><br>
    Enter pass: <input type="password" name="pass"><br>
    <input type="submit" value="Login">
</form>

</body>
</html>
