<%@ taglib prefix="s" uri="/struts-tags" %>
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
    <script src=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js "></script>
    <link href=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css " rel="stylesheet">
</head>
<body>
<h2>
    <s:property value="session.user.login" /></h2>
    <p>
        La mise de ${pari.montant} euros sur le résultat ${pari.vainqueur} pour le match : ${pari.match.equipe1} vs ${pari.match.equipe2} le ${pari.match.quand} a bien été annulée !
    </p>
    <s:a action="connexion">Retour au menu</s:a>
</body>
</html>
