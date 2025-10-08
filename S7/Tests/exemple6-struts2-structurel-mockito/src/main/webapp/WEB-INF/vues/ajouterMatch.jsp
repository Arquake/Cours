<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Saisir un match</title>
</head>
<body>
<h2><s:property value="utilisateur.login"/></h2>


<s:form action="ajouterMatch">
    <s:textfield name="sport" label="sport"/>
    <s:textfield name="equipe1" label="équipe 1"/>
    <s:textfield name="equipe2" label="équipe 2"/>
    <s:textfield name="quand" label="date/heure au format yyyy-MM-dd HH:mm"/>
    <s:submit/>
</s:form>

<s:a action="goMenu">Retour au menu principal</s:a>

</body>
</html>
