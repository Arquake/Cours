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

<c:choose>
    <c:when test="${not empty connected and connected}">
        <p>
            <span style="color: red">Couple pseudo/password incohérent</span>
        </p>
    </c:when>
    <c:when test="${not empty incoherent and incoherent}">
        <p>
            <span style="color: red">Le pseudo est obligatoire et de taille 2 min. Le mot de passe est obligatoire et de taille 2 min</span>
        </p>
    </c:when>
</c:choose>


<form action="/pel/connexion" method="post">
        <label for="pseudo">Pseudo</label>
        <input type="text" id="pseudo" name="pseudo">
        <label for="password">Password</label>
        <input type="password" id="password" name="password">
        <button type="submit">Connexion!</button>
    </form>
</body>
</html>
