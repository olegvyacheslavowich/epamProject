<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/localization.tld" prefix="l" %>
<html>
<head>
    <title><l:localization key="label.private_office"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/user.css"/>"/>
</head>
<body>

<c:if test="${sessionScope.user eq null}">
    <jsp:forward page="login.jsp"/>
</c:if>

<jsp:include page="header.jsp"/>
<aside>
    <form name="clientData" method="post" action="/servlet"/>
    <input type="hidden" name="command" value="clientData">
    <label class="info"><l:localization key="label.login"/>: ${user.account.login} <br>
        <l:localization key="label.full_name"/>: ${user.fullName} <br>
        <l:localization key="label.birthday"/>: ${user.birthday}<br>
        <l:localization key="label.paper"/>: ${user.paper} <br>
        <l:localization key="label.paper_num"/>: ${user.documentNum}<br>
        <l:localization key="label.mobile"/>: ${user.phone} <br>
        <l:localization key="label.email"/>: ${user.email}
    </label>
    </form>
</aside>

<div class="blocktable2">
    <table class="k">
        <tr>
            <th class="one"><l:localization key="label.owner"/></th>
            <th class="two"><l:localization key="label.card_num"/></th>
            <th class="three">CVV</th>
            <th class="four"><l:localization key="label.date"/></th>
            <th class="five"><l:localization key="label.card_type"/></th>
            <th class="nine"><l:localization key="label.money_now"/></th>
        </tr>

        <c:forEach var="money" items="${moneys}">
            <tr>
                <td>${money.creditCard.ownerName} </td>
                <td>${money.creditCard.number}</td>
                <td>${money.creditCard.cvvNumber}</td>
                <td>${money.creditCard.date}</td>
                <td>${money.creditCard.cardType.name}</td>
                <td>${money.money}</td>
            </tr>

        </c:forEach>
    </table>
</div>
<a href="/jsp/card.jsp" class="sl"><l:localization key="label.add_card"/></a>


<div class="blocktable3">
    <form name="myTours" method="post" action="/servlet">
        <input type="hidden" name="command" value="refuse">
        <table class="z">
            <tr>
                <th class="one"><l:localization key="label.full_name"/></th>
                <th class="one"><l:localization key="label.country"/></th>
                <th class="two"><l:localization key="label.city"/></th>
                <th class="four"><l:localization key="label.days"/></th>
                <th class="five"><l:localization key="label.hotel"/></th>
                <th class="seven"><l:localization key="label.stars"/></th>
                <th class="seven"><l:localization key="label.departure"/></th>
                <th class="seven"><l:localization key="label.to"/></th>
                <th class="seven"><l:localization key="label.from"/></th>
                <th class="eight"><l:localization key="label.price"/></th>

            </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.client.fullName} </td>
                    <td>${order.voucher.tour.city.country.name}</td>
                    <td>${order.voucher.tour.city.name}</td>
                    <td>${order.voucher.tour.days}</td>
                    <td>${order.voucher.tour.hotel.name}</td>
                    <td>${order.voucher.tour.hotel.starsNumber}</td>
                    <td>${order.voucher.flightTo.plane.departureCity.name}</td>
                    <td>${order.voucher.flightTo.date} _ ${order.voucher.flightTo.plane.departureTime}</td>
                    <td>${order.voucher.flightFrom.date} _ ${order.voucher.flightFrom.plane.departureTime}</td>
                    <td>${order.voucher.price}</td>
                    <td><input type="radio" class="radio" name="orderId" value="${order.id}"/></td>
                </tr>


            </c:forEach>
        </table>
</div>
<input type="submit" name="refuse" class="send" value="<l:localization key="label.refuse"/>">
</form>
</body>
</html>
