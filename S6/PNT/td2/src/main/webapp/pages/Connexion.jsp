<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14/01/2025
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Paris En Ligne</title>
    <jsp:useBean id="connexionBean" scope="request" type="beans.ConnexionErrorBean"/>
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"
    >
</head>
<body class="container">

<c:choose>
    <c:when test="${not empty connexionBean && connexionBean.alreadyConnectedError == true}">
        <p>
            <span style="color: red">Couple pseudo/password incohérent</span>
        </p>
    </c:when>
    <c:when test="${not empty connexionBean && connexionBean.incoherentError == true}">
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
