<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Annulation d'un pari sur un match</title>
</head>
<body>
<h2><s:property value="utilisateur.login"/></h2>

Voulez-vous vraiment annuler votre pari sur le match de <s:property value="pari.match.sport"/> :
<s:property value="pari.match.equipe1"/>
VS
<s:property value="pari.match.equipe2"/>
le
<s:property value="pari.match.quand"/>

<p>
    avec un résultat : <s:property value="pari.vainqueur"/>
    pour le montant : <s:property value="pari.montant"/>
</p>
<s:form action="annulerPari">
    <s:hidden name="idPari" value="%{pari.idPari}"/>
    <s:if test="hasActionErrors()">
        <div class="errors">
            <s:actionerror/>
        </div>
    </s:if>
    <s:submit/>
</s:form>

<s:a action="goMenu">Retour au menu principal</s:a>

</body>
</html>
