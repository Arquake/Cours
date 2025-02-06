<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24/01/2025
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body class="container">
    <h1>Connexion</h1>
    <div role="alert">
        <s:actionerror cssClass="alert alert-danger"/>
    </div>
    <s:form action="saisie">
        <div class="mb-3">
            <s:label for="pseudoInput" class="form-label">Pseudo</s:label>
            <s:textfield
                    name="pseudo"
                    cssClass="form-control"
                    id="pseudoInput"
                    aria-describedby="pseudoHelp" />
            <div id="pseudoHelp" class="form-text">Votre pseudo.</div>
        </div>
        <div class="mb-3">
            <s:label for="passwordInput" class="form-label">Password</s:label>
            <s:password
                    name="password"
                    cssClass="form-control"
                    id="passwordInput" />
        </div>
        <s:submit value="Submit" cssClass="btn btn-primary" />
    </s:form>

</body>
</html>
