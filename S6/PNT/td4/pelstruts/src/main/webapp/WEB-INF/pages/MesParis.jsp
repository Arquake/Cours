<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14/01/2025
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Paris en ligne</title>
    <script src=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js "></script>
    <link href=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css " rel="stylesheet">
</head>
<body class="container">
    <h2><s:property value="session.user.login" /></h2>

    <s:if test="hasActionErrors()">
        <ul class="errors alert alert-danger">
            <s:actionerror />
        </ul>
    </s:if>

    <ul>
        <s:iterator value="paris" var="pari">
            <li>
                <p>sport : ${pari.match.sport} - ${pari.match.equipe1} vs ${pari.match.equipe2} - ${pari.match.quand}. Mise de ${pari.montant} sur ${pari.vainqueur}</p>
                <s:form action="annulerParis">
                    <s:hidden value="%{idPari}" name="pariId"/>
                    <s:submit value="Annuler" class="btn btn-primary"/>
                </s:form>
            </li>
        </s:iterator>
    </ul>

    <s:a href="/pel/connexion">Retour au menu</s:a>
</body>
</html>
