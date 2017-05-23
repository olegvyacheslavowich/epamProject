<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/localization.tld" prefix="l" %>
<html>
<head>
    <title><l:localization key="label.come_in"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/login.css"/>"/>
</head>
<body>

<jsp:include page="header.jsp"/>

<c:if test="${sessionScope.user ne null}">
    <jsp:forward page="index.jsp"/>
</c:if>

<c:if test="${sessionScope.admin.account.login ne null}">
    <jsp:forward page="index.jsp"/>
</c:if>

<aside>
    <form name="login" method="POST" action="/servlet">
        <input type="hidden" name="command" value="login"/>

        <label class="login2"> <l:localization key="label.login"/></label>
        <input type="text" class="login3" name="login" value=""/>

        <label class="password2"><l:localization key="label.password"/></label>
        <input type="password" class="password3" name="password" value=""/>

        <input type="submit" class="enter" value="<l:localization key="label.come_in"/>"/>
    </form>
</aside>

</body>
</html>
