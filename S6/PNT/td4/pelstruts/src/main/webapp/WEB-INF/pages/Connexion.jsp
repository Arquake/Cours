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
        <s:actionerror cssClass="errors alert alert-danger"/>
    </s:if>

    <s:form action="connexion">
        <div class="form-group">
            <s:textfield id="login" name="login" label="Login" cssClass="form-control" />
        </div>
        <div class="form-group">
            <s:textfield id="password" name="password" label="Mot De Passe" cssClass="form-control"/>
        </div>
        <s:submit value="Soumettre" cssClass="btn btn-primary"/>
    </s:form>
</body>
</html>
