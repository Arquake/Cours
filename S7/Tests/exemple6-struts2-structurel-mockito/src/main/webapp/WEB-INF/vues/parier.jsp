<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parier sur un match</title>
</head>
<body>
<h2><s:property value="utilisateur.login"/></h2>

Parier sur le match de <s:property value="match.sport"/> :
<s:property value="match.equipe1"/>
VS
<s:property value="match.equipe2"/>
le
<s:property value="match.quand"/>

<s:form action="parier">
    <s:hidden name="idMatch" value="%{match.idMatch}"/>
    <s:select list="resultatsPossibles" name="resultat"/>
    <s:textfield name="montant" label="montant"/>
    <s:submit/>
</s:form>

<s:a action="goMenu">Retour au menu principal</s:a>

</body>
</html>
