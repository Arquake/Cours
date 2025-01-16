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
    <jsp:useBean id="pari" type="modele.Pari" scope="request"/>
    <jsp:useBean id="user" type="modele.Utilisateur" scope="session"/>
</head>
<body>
    <p>${user.login}</p>
    <p>
        Vous avez parié ${pari.montant} euros sur le résultat ${pari.vainqueur} pour le match : ${pari.match.equipe1} vs ${pari.match.equipe2} le ${pari.match.quand}
    </p>
    <a href="/pel/connexion">Retour au menu</a>
</body>
</html>
