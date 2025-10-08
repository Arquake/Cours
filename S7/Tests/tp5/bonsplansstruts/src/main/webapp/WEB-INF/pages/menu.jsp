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
<H1>MEILLEURSPLANS.COM</H1>

<s:if test="hasActionMessages()">
    <span style="color: green; "><s:actionmessage/></span>
</s:if>


<s:if test="hasActionErrors()">
    <span style="color: red; "><s:actionerror/></span>
</s:if>


<s:if test="%{utilisateur.role == 'MODERATEUR'}">

    <h1>Menu modérateur</h1>
    <ul>
        <li><s:a action="gotocandidatures">Traiter les demandes</s:a></li>
        <li><s:a action="gotoajouterbonplan"> Ajouter un bon plan</s:a></li>
        <li><s:a action="gotomesbonsplans"> Mes bons plans</s:a></li>
        <li><s:a action="gotolesbonsplans">Consulter les bons plans</s:a></li>
    </ul>
</s:if>
<s:else>
    <h1>Menu étudiant</h1>
    <ul>
        <li><s:a action="candidater">Candidater pour modération</s:a></li>
        <li><s:a action="gotoajouterbonplan"> Ajouter un bon plan</s:a></li>
        <li><s:a action="gotomesbonsplans"> Mes bons plans</s:a></li>
        <li><s:a action="gotolesbonsplans">Consulter les bons plans</s:a></li>
    </ul>
</s:else>


</body>
</html>
