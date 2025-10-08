<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste de tous les matchs</title>
</head>
<body>
<h2><s:property value="utilisateur.login"/></h2>

        <table>
        <s:iterator var="match" value="matchs">
            <tr>
                <td><s:property value="#match.sport"/> </td>
                <td><s:property value="#match.equipe1"/> </td>
                <td><s:property value="#match.equipe2"/> </td>
                <td><s:property value="#match.quand"/> </td>
                <td>
                <s:if test="%{#match.resultat.isPresent}">
                    Résultat : <s:property value="#match.resultat.get"/>
                </s:if>
                <s:else>
                    <s:url action="goResultat" var="url">
                        <s:param name="idMatch" value="#match.idMatch"/>
                    </s:url>
                    <s:if test="%{utilisateur.isAdmin}">
                    <s:a href="%{url}">Saisir le résultat</s:a>
                    </s:if>
                    <s:else>Pas de résultat</s:else>
                </s:else>
                </td>
            </tr>
        </s:iterator>
        </table>

    <s:a action="goMenu">Retour au menu principal</s:a>


</body>
</html>
