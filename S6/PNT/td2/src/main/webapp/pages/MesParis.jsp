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
    <title>Title</title>
    <jsp:useBean id="user" type="modele.Utilisateur" scope="session"/>
    <jsp:useBean id="paris" type="java.util.Collection<modele.Pari>" scope="request"/>
    <jsp:useBean id="cancelError" type="java.lang.Boolean" scope="request"/>
</head>
<body>
    <p>${user.login}</p>

    <c:if test="${cancelError}">
        <p>
            <span style="color: red">Le pari ne peut pas être annulé !</span>
        </p>
    </c:if>

    <ul>
        <c:forEach items="${paris}" var="pari" >
            <li>
                sport : ${pari.match.sport} - ${pari.match.equipe1} vs ${pari.match.equipe2} - ${pari.match.quand}. Mise de ${pari.montant} sur ${pari.vainqueur}
                <a href="/pel/annulerpari?id=${pari.idPari}">
                    annuler
                </a>
            </li>
        </c:forEach>
    </ul>

    <a href="/pel/connexion">Retour au menu</a>
</body>
</html>
