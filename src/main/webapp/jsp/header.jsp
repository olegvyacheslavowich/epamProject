<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<%@ taglib prefix="l" uri="/WEB-INF/tags/localizationTag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="header.css">
</head>
<body>

<header>
<a href="#" class="find"> Поиск </a>

    <form name="login" method="GET" action="/servlet">
        <input type="hidden" name="translator" value="${pageContext.request.servletPath}"/>
        <input type="submit" class="RU" name="language" value=" "/>
        <input type="submit" class="ENG" name="language" value="  "/>
    </form>


    <c:if test="${sessionScope.user eq null}">
        <a href="/jsp/login.jsp" class="login"> <l:localization key="label.come_in"/></a>
        <a href="/jsp/registration.jsp" class="registration"><l:localization key="label.registration"/></a>
        <br/>
     </c:if> 
    <c:if test="${sessionScope.user ne null}"> 
        <form name="logout" method="POST" action="/servlet">
            <input type="hidden" name="command" value="logout">
            <input type="submit" class="exit" name="logout" value=" <l:localization key="label.come_out"/>"/>
        </form>
       <c:if test="${sessionScope.admin.account.login eq null}">
            <form name="user" method="post" action="/servlet">
                <input type="hidden" name="command" value="user">
                <input type="submit" class="myK" name="user" value=" <l:localization key="label.private_office"/>">
            </form>
       </c:if> 
        <c:if test="${sessionScope.admin.account.login ne null}">
            <form name="user" method="post" action="/servlet">
                <input type="hidden" name="command" value="admin">
                <input type="submit" class="adminka" name="admin" value="<l:localization key="label.admin"/>">
            </form>
       </c:if>
  </c:if> 
    <label class="error">${exception}</label>
</header>
</body>
</html>
