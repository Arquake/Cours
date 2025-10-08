<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Saisir le résultat d'un match</title>
</head>
<body>
<h2><s:property value="utilisateur.login"/></h2>

Saisir le résultat du match de <s:property value="match.sport"/> :
<s:property value="match.equipe1"/>
VS
<s:property value="match.equipe2"/>
le
<s:property value="match.quand"/>

<s:form action="resultat">
    <s:hidden name="idMatch" value="%{match.idMatch}"/>
    <s:select list="resultatsPossibles" name="resultat"/>
    <s:submit/>
</s:form>

<s:a action="goMenu">Retour au menu principal</s:a>

</body>
</html>
