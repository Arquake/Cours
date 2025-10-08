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
<H1>Les bons plans</H1>


<s:if test="hasActionMessages()">
    <span style="color: green; "><s:actionmessage/></span>
</s:if>


<s:if test="hasActionErrors()">
    <span style="color: red; "><s:actionerror/></span>
</s:if>



<ul>
<s:iterator value="%{bonsPlans}" var="x">
    <s:url action="selectionnerbonplan" var="url1">
        <s:param name="idBonPlan"><s:property value="#x.id"/> </s:param>
    </s:url>
    <li><s:property value="#x.description"/> <s:property value="#x.prix"/>
     <s:property value="#x.dateDebut"/> <s:property value="#x.dateFin"/>
        (<s:a href="%{#url1}">Modifier</s:a>)  </li>
</s:iterator>
</ul>


<s:a action="backtomenu">Menu principal</s:a>
</body>
</html>
