<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Nicolas
  Date: 28/01/2025
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js "></script>
    <link href=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css " rel="stylesheet">
</head>
<body class="container">
    <s:if test="hasActionErrors()">
        <ul class="errors alert alert-danger">
            <s:actionerror />
        </ul>
    </s:if>

    <s:form action="connexion">
        <div class="form-group">
            <s:label for="login">Login</s:label>
            <s:textfield id="login" name="login"/>
        </div>
        <div class="form-group">
            <s:label for="password">Mot de passe</s:label>
            <s:textfield id="password" name="password"/>
        </div>
        <s:submit value="Soumettre"/>
    </s:form>
</body>
</html>
