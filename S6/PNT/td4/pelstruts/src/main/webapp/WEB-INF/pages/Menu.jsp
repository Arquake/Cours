<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Nicolas
  Date: 16/01/2025
  Time: 11:22
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
    <h1>Menu</h1>
    <ul>
        <li>
            <s:a action="parisouverts">Afficher les matchs sur lesquels parier</s:a>
        </li>
        <li>
            <s:a action="mesParis">Afficher mes paris</s:a>
        </li>
        <li>
            <s:a action="logout">Déconnexion</s:a>
        </li>
    </ul>
</body>
</html>
