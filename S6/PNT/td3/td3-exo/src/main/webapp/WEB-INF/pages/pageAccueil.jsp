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
    <title><s:text name="pageAccueil.titre"/></title>*
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
    <nav>
        <ul>
            <li>
                <s:a action="connexion"><s:text name="pageAccueil.linkConnexion"/></s:a>
            </li>
            <li>
                <s:a action="calculatriceStatique"><s:text name="pageAccueil.linkStatique"/></s:a>
            </li>
            <li>
                <s:a action="calculatriceDynamique"><s:text name="pageAccueil.linkDynamique"/></s:a>
            </li>
        </ul>
    </nav>
</body>
</html>
