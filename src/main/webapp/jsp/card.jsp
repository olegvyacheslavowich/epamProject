<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/localization.tld" prefix="l" %>
<html>
<head>
    <title><l:localization key="label.credit_card"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/card.css"/>"/>
</head>
<body>

<jsp:include page="header.jsp"/>


<c:if test="${sessionScope.user eq null}">
    <jsp:forward page="login.jsp"/>
</c:if>

<c:if test="${sessionScope.admin.account.login ne null}">
    <jsp:forward page="login.jsp"/>
</c:if>

<aside>
    <form name="card" method="POST" action="/servlet">
        <input type="hidden" name="command" value="card"/>

        <label class="owner"><l:localization key="label.owner"/></label>
        <input type="text" name="ownerName" class="owner1" value=""/>

        <label class="number"><l:localization key="label.card_num"/></label>
        <input type="number" name="number" class="number1" value=""/>

        <label class="cvv">CVV</label>
        <input type="number" name="cvvNumber" class="cvv1" value=""/>

        <label class="date33"><l:localization key="label.date"/></label>
        <input type="date" name="date" class="date34" value=""/>

        <label class="type"><l:localization key="label.card_type"/></label>
        <select name="type" class="type1">
            <option value="VISA">VISA</option>
            <option value="MASTER CARD">MASTER CARD</option>
        </select>

        <input type="submit" class="append" value="<l:localization key="label.add_card"/>"/>
    </form>
</aside>
</body>
</html>
