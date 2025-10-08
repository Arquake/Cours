<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mes paris</title>
</head>
<body>
<h2><s:property value="utilisateur.login"/></h2>

        <table>
        <s:iterator var="pari" value="paris">
            <tr>
                <td><s:property value="#pari.match.sport"/> </td>
                <td><s:property value="#pari.match.equipe1"/> </td>
                <td><s:property value="#pari.match.equipe2"/> </td>
                <td>vous avez parié sur <s:property value="#pari.vainqueur"/> </td>
                <td><s:property value="#pari.montant"/> € </td>
                <td>
                    <s:if test="%{#pari.gain.isPresent}">
                        gain de <s:property value="#pari.gain.get"/> €
                    </s:if>
                    <s:else>
                        <s:url action="goAnnulerPari" var="pari">
                            <s:param name="idPari" value="#pari.idPari"/>
                        </s:url>
                        <s:a href="%{pari}">Annuler le pari</s:a>
                    </s:else>
                </td>
            </tr>
        </s:iterator>
        </table>

    <s:a action="goMenu">Retour au menu principal</s:a>


</body>
</html>
