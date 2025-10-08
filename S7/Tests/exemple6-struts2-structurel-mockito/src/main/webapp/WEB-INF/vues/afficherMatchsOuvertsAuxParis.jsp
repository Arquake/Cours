<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Matchs ouverts aux paris</title>
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
                <s:url action="goParier" var="parier">
                    <s:param name="idMatch" value="#match.idMatch"/>
                </s:url>
                <s:a href="%{parier}">Parier</s:a>
                </td>
            </tr>
        </s:iterator>
        </table>

    <s:a action="goMenu">Retour au menu principal</s:a>


</body>
</html>
