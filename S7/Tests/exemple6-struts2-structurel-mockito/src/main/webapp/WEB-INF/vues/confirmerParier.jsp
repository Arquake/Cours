<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirmation d'un pari sur un match</title>
</head>
<body>
<h2><s:property value="utilisateur.login"/></h2>

Votre pari sur le match de <s:property value="match.sport"/> :
<s:property value="match.equipe1"/>
VS
<s:property value="match.equipe2"/>
le
<s:property value="match.quand"/>

<p>
    résultat : <s:property value="pari.vainqueur"/>
    montant : <s:property value="pari.montant"/>
</p>

<p>est enregistré.</p>

<s:a action="goMenu">Retour au menu principal</s:a>

</body>
</html>
