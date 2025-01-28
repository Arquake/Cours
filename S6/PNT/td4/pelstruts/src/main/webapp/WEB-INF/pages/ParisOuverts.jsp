<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14/01/2025
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Paris En Ligne</title>
    <jsp:useBean id="matchs" type="java.util.Collection<modele.Match>" scope="request"/>
    <jsp:useBean id="user" type="modele.Utilisateur" scope="session"/>
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"
    >
</head>
<body class="container">
    <h2>${user.login}</h2>

    <ul>
        <c:forEach items="${matchs}" var="match" >
            <li style="display: flex; justify-items: center; align-items: center">
                <div style="display:flex; gap:.5rem;">
                    <div style="display: flex; justify-items: center; align-items: center">
                        <p style="text-align: center">sport : ${match.sport} - ${match.equipe1} vs ${match.equipe2} - </p>
                    </div>
                    <form action="/pel/parier" method="post">
                        <input name="matchId" value="${match.idMatch}" hidden="hidden">
                        <button type="submit">Parier</button>
                    </form>
                </div>
            </li>
        </c:forEach>
    </ul>

    <a href="/pel/connexion">Retour au menu</a>
</body>
</html>
