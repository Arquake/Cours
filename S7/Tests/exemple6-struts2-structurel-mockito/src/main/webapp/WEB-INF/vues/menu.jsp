<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Paris en ligne</title>
</head>
<body>

<h2>Bonjour <s:property value="utilisateur.login"/></h2>
<s:if test="%{utilisateur.isAdmin}">
    <h3>Vous êtes admin</h3>
</s:if>

<ul>
    <li><s:a action="goMatchsOuvertAuxParis">Afficher les matchs sur lesquels parier</s:a></li>
    <li><s:a action="goMesParis">Afficher mes paris</s:a></li>
    <s:if test="%{utilisateur.isAdmin}">
        <li><s:a action="goAjouterMatch">Ajouter un nouveau match</s:a></li>
        <li><s:a action="goMatchs">Afficher tous les matchs pour saisie résultat</s:a></li>
    </s:if>
    <li><s:a action="logout">Logout</s:a></li>
</ul>

</body>
</html>
