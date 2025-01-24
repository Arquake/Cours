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
    <title>Paris En Ligne</title>
    <jsp:useBean id="pariAnnule" type="modele.Pari" scope="request"/>
    <jsp:useBean id="user" type="modele.Utilisateur" scope="session"/>
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"
    >
</head>
<body>
    <h2>${user.login}</h2>
    <p>
        La mise de ${pariAnnule.montant} euros sur le résultat ${pariAnnule.vainqueur} pour le match : ${pariAnnule.match.equipe1} vs ${pariAnnule.match.equipe2} le ${pariAnnule.match.quand} a bien été annulée !
    </p>
    <a href="/pel/connexion">Retour au menu</a>
</body>
</html>
