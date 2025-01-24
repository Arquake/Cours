<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24/01/2025
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accueil</title>
</head>
<body>
    <nav>
        <ul>
            <li>
                <s:a action="connexion">Connexion</s:a>
            </li>
            <li>
                <s:a action="calculatriceStatique">Calculatrice statique</s:a>
            </li>
            <li>
                <s:a action="calculatriceDynamique">Calculatrice dynamique</s:a>
            </li>
        </ul>
    </nav>
</body>
</html>
