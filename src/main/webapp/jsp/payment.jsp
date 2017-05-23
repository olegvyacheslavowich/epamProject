<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/localization.tld" prefix="l" %>
<html>
<head>
    <title><l:localization key="label.payment"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/payment.css"/>"/>
</head>
<body>
<jsp:include page="header.jsp"/>

<c:if test="${sessionScope.tour eq null}">
    <jsp:forward page="index.jsp"/>
</c:if>

<aside>
    <form name="payment" method="post" action="/servlet">
        <input type="hidden" name="command" value="payment">

        <label class="text1"><l:localization key="label.tourist_num"/>: ${adultNumber + childrenNumber} </label>
        <label class="text2"><l:localization key="label.tour_price"/>: ${tour.price} </label>
        <label class="text3"><l:localization key="label.total_price"/>: ${voucher.price}</label>

        <label class="text4"> <l:localization key="label.credit_card"/>: </label>
        <select name="moneyId" class="money">
            <c:forEach var="money" items="${moneys}">
                <option value="${money.id}"><l:localization key="label.card_num"/>: ${money.creditCard.number}
                    <l:localization key="label.money_now"/>: ${money.money}</option>
            </c:forEach>
        </select>
        <input type="submit" name="choose" class="choose9" value="<l:localization key="label.pay_for_ticket"/>"/>
    </form>
</aside>
</body>
</html>
