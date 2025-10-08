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
<H1>Candidatures pour modération</H1>

<s:if test="%{candidatures.size() == 0}">
    <h2>Aucune candidature à traiter !</h2>
</s:if>


<s:if test="hasActionMessages()">
    <span style="color: green; "><s:actionmessage/></span>
</s:if>


<s:if test="hasActionErrors()">
    <span style="color: red; "><s:actionerror/></span>
</s:if>



<ul>
<s:iterator value="%{candidatures}" var="x">
    <s:url action="validercandidature" var="url">
        <s:param name="email"><s:property value="#x.email"/> </s:param>
    </s:url>
    <s:url action="refusercandidature" var="url2">
        <s:param name="email"><s:property value="#x.email"/> </s:param>
    </s:url>
    <li><s:property value="#x.nom"/> <s:property value="#x.prenom"/>
     <s:property value="#x.email"/> (<s:a href="#url">Accepter</s:a> / <s:a href="#url2">Refuser</s:a>)  </li>
</s:iterator>
</ul>
</body>
</html>
