<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14/01/2025
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/pel/home" method="post">
        <label for="pseudo">Pseudo</label>
        <input type="text" id="pseudo" name="pseudo">
        <label for="password">Password</label>
        <input type="password" id="password" name="password">
        <button type="submit">Connexion!</button>
    </form>
</body>
</html>
