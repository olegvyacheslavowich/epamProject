<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<%@ taglib uri="/WEB-INF/tld/localization.tld" prefix="l" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><l:localization key="label.registration"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/registration.css"/>"/>

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
    <form name="registration" method="post" action="/servlet">
        <input type="hidden" name="command" value="registration">

        <label class="login5"> <l:localization key="label.login"/></label>
        <input type="text" class="login6" name="login" value="">

        <label class="name"> <l:localization key="label.full_name"/> </label>
        <input type="text" class="name1" name="fullName" value="">

        <label class="year"><l:localization key="label.birthday"/></label>
        <input type="date" class="year1" name="year" value="">

        <label class="doc"><l:localization key="label.paper"/></label>
        <td><select name="paper" class="doc1">
            <option value="passport"><l:localization key="label.passport"/></option>
            <option value="identityСard"><l:localization key="label.id_card"/></option>
            <option value="birthСertificate"><l:localization key="label.birth_certificate"/></option>
        </select></td>

        <label class="numberdoc"><l:localization key="label.paper_num"/></label>
        <input type="text" name="documentNum" class="numberdoc1" value="">


        <label class="mobile"> <l:localization key="label.mobile"/> </label>
        <input type="text" class="mobile1" name="phone" value="">

        <label class="email"> <l:localization key="label.email"/> </label>
        <input type="text" class="email1" name="email" value="">

        <label class="pass"><l:localization key="label.password"/> </label>
        <input type="password" class="pass1" name="password" value="">

        <label class="rpassword"> <l:localization key="label.rpassword"/> </label>
        <input type="password" class="rpassword1" name="rpassword" value="">


        <input type="submit" class="regist" name="registration" value="<l:localization key="label.registration"/>">
    </form>
</aside>


</body>
</html>
