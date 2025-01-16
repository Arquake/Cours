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
    <title>Title</title>
    <jsp:useBean id="matchs" type="java.util.Collection<modele.Match>" scope="request"/>
    <jsp:useBean id="user" type="modele.Utilisateur" scope="session"/>
</head>
<body>
    <p>${user.login}</p>

    <ul>
        <c:forEach items="${matchs}" var="match" >
            <li style="display: flex; justify-items: center; align-items: center">
                <p>sport : ${match.sport} - ${match.equipe1} vs ${match.equipe2} - </p>
                <form action="/pel/parier" method="post">
                    <input name="matchId" value="${match.idMatch}" hidden="hidden">
                    <button type="submit">Parier</button>
                </form>
            </li>
        </c:forEach>
    </ul>

    <a href="/pel/connexion">Retour au menu</a>
</body>
</html>
