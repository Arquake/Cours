<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14/01/2025
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Paris  En Ligne</title>
    <script src=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js "></script>
    <link href=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css " rel="stylesheet">
</head>
<body class="container">
    <h2><s:property value="session.user.login" /></h2>

    <s:if test="hasActionErrors()">
        <div class="errors alert alert-danger">
            <s:actionerror />
        </div>
    </s:if>

    <p>
        Vous voulez parier sur le match :
        <s:property value="match.equipe1"/> vs
        <s:property value="match.equipe2"/> le
        <s:property value="match.quand"/>
    </p>

    <s:form action="parierSubmit">
        <s:label for="vainqueur">Verdict du match</s:label>
        <s:select
                name="vainqueur"
                id="vainqueur"
                list="{'nul' ,match.equipe1, match.equipe2}"
                cssClass="form-control" size="1"
        />

        <s:label for="montant">Montant</s:label>
        <s:textfield type="number" name="montant" id="montant" />

        <s:hidden name="idMatch" value="%{match.idMatch}" />

        <s:submit value="Gamble !!!!!" cssClass="btn btn-primary"/>
    </s:form>

    <s:a action="connexion">Retour au menu</s:a>
</body>
</html>
