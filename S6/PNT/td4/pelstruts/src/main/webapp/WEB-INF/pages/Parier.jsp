<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14/01/2025
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Paris  En Ligne</title>
    <jsp:useBean id="match" type="modele.Match" scope="request"/>
    <jsp:useBean id="user" type="modele.Utilisateur" scope="session"/>
    <jsp:useBean id="errorMise" type="java.lang.Boolean" scope="request"/>
    <jsp:useBean id="verdict" type="java.lang.String" scope="request"/>
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"
    >
</head>
<body class="container">
    <h2>${user.login}</h2>

    <p>
        Vous voulez parier sur le match : ${match.equipe1} vs ${match.equipe2} le ${match.quand}
        <c:if test="${errorMise}">
            <span style="color: red">Vous devez saisir un montant positif pour votre mise !</span>
        </c:if>
    </p>

    <form action="/pel/parier" method="post">
        <label id="choice" for="verdict">Verdict du match</label>
        <select name="verdict" id="verdict">
            <option>nul</option>
            <option <c:if test="${verdict==match.equipe1}">selected</c:if> >
                ${match.equipe1}
            </option>
            <option <c:if test="${verdict==match.equipe2}">selected</c:if> >
                ${match.equipe2}
            </option>
        </select>

        <label id="montant">Montant</label>
        <input type="number" name="mise">

        <input type="number" name="matchId" value="${match.idMatch}" hidden="hidden">

        <button type="submit">parier !</button>
    </form>

    <a href="/pel/connexion">Retour au menu</a>
</body>
</html>
