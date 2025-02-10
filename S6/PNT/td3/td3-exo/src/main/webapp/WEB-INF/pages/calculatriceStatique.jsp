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
    <title>Calculatrice statique</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body class="container">
    <s:form action="calculatriceStatiqueCalcul">
        <div class="mb-3">
            <label for="operande1Input" class="form-label">Premier nombre</label>
            <s:textfield
                    name="operand1"
                    cssClass="form-control"
                    id="operande1Input"
                    type="number"/>
        </div>
        <div class="mb-3">
            <label for="operande2Input" class="form-label">Deuxième nombre</label>
            <s:textfield
                    name="operand2"
                    cssClass="form-control"
                    id="operande2Input"
                    type="number"/>
        </div>

        <div class="mb-3">
            <label for="operand" class="form-label">Opérande</label>
            <s:select
                    cssClass="form-control"
                    name="operand"
                    list="operands"
                    size="1"
                    id="operand"/>
        </div>

        <s:submit value="Submit" cssClass="btn btn-primary" />
    </s:form>
</body>
</html>
