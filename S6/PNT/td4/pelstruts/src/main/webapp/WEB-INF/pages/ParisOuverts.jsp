<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14/01/2025
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Paris En Ligne</title>
    <script src=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js "></script>
    <link href=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css " rel="stylesheet">
</head>
<body class="container">
    <h2><s:property value="session.user.login" /></h2>

    <ul>
        <s:iterator value="matchs" var="match" >
            <li style="display: flex; justify-items: center; align-items: center">
                <div style="display:flex; gap:.5rem;">
                    <div style="display: flex; justify-items: center; align-items: center">
                        <p style="text-align: center">sport : ${sport} - ${equipe1} vs ${equipe2} - </p>
                    </div>
                    <s:form action="parier">
                        <s:hidden name="idMatch" value="%{idMatch}" hidden="hidden" />
                        <s:submit value="GAMBLE!!!!!" cssClass="btn btn-primary"/>
                    </s:form>
                </div>
            </li>
        </s:iterator>
    </ul>

    <a href="/pel/connexion">Retour au menu</a>
</body>
</html>
