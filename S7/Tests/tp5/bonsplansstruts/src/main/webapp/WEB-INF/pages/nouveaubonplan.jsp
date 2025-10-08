<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: yoh
  Date: 17/05/2024
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MEILLEURSPLANS.COM</title>
</head>
<body>
<H1>Saisissez votre nouveau bon plan</H1>

<s:if test="hasActionMessages()">
    <span style="color: green; "><s:actionmessage/></span>
</s:if>


<s:if test="hasActionErrors()">
    <span style="color: red; "><s:actionerror/></span>
</s:if>

<s:form action="saisirnouveaubonplan" method="POST">

    <s:textfield name="thematique" label="Thème du bon plan"/>
    <s:textfield name="description" label="Description du bon plan"/>
    <s:textfield label="Lien/contacts" name="lien"/>
    <s:textfield name="dateDebut" label="Date de début (au format jj/mm/aaaa)"/>
    <s:textfield name="dateFin" label="Date de début (au format jj/mm/aaaa)"/>
    <s:textfield label="Prix du bon plan" name="prix"/>

    <s:submit/>
</s:form>




<s:a action="backtomenu">Menu principal</s:a>


</body>
</html>
