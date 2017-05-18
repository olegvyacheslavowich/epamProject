<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/localization.tld" prefix="l" %>
<html>
<head>
    <title>Оплата</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/payment.css"/>"/>
</head>
<body>
<jsp:include page="header.jsp"/>

<c:if test="${sessionScope.user eq null}">
    <jsp:forward page="login.jsp"/>
</c:if>

<aside>
    <form name="payment" method="post" action="/servlet">
        <input type="hidden" name="command" value="payment">

        <label class="text1"> Количество туристов: ${adultNumber + childrenNumber} </label>
        <label class="text2">Стоимость тура: ${tour.price} </label>
        <label class="text3">Сумма к оплате: ${voucher.price}</label>

        <label class="text4"> Карточка: </label>
        <select name="moneyId" class="money">
            <c:forEach var="money" items="${moneys}">
                <option value="${money.id}">Номер: ${money.creditCard.number} Баланс: ${money.money}</option>
            </c:forEach>
        </select>
        <input type="submit" name="choose" class="choose9" value="Оплатить">
    </form>
</aside>
</body>
</html>
