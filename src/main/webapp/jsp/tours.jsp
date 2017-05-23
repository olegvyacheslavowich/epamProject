<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/localization.tld" prefix="l" %>
<html>
<head>
    <title><l:localization key="label.tours"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/tours.css"/>"/>
</head>
<body>
<jsp:include page="header.jsp"/>

<c:if test="${sessionScope.tours eq null}">
    <jsp:forward page="index.jsp"/>
</c:if>

<div class="blocksort">
    <form name="toursSort" method="post" action="/servlet">
        <input type="hidden" name="command" value="toursSorting">

        <input type="radio" class="pr" name="sort" value="price"><label class="pri"><l:localization
            key="label.by_price"/> </label>
        <div class="v1"></div>
        <input type="radio" class="day" name="sort" value="days"><label class="days"> <l:localization
            key="label.by_days"/> </label>
        <div class="v2"></div>
        <input type="radio" name="sort" class="hot" value="hotTours"> <label class="hooot"><l:localization
            key="label.by_hot_tours"/></label>
        <input type="submit" class="sub" value="<l:localization key="label.sort"/>">

    </form>


    <form name="tours" method="post" action="/servlet">
        <input type="hidden" name="command" value="tours">

        <c:if test="${sessionScope.user ne null}">
        <input type="submit" name="choose" class="select" value="<l:localization key="label.order"/>"></td>
        </c:if>


        <c:if test="${sessionScope.date eq ''}">
        <label class="date3"><l:localization key="label.departure_date"/> </label>
        <input type="date" name="departureDate" class="departureDate" value=""> <br>
        </c:if>

</div>

<div class="blocktable">
    <table>
        <tr>
            <th class="one"><l:localization key="label.country"/></th>
            <th class="two"><l:localization key="label.city"/></th>
            <th class="three"><l:localization key="label.description"/></th>
            <th class="four"><l:localization key="label.days"/></th>
            <th class="five"><l:localization key="label.hotel"/></th>
            <th class="six"><l:localization key="label.about_hotel"/></th>
            <th class="seven"><l:localization key="label.stars"/></th>
            <th class="eight"><l:localization key="label.price"/></th>
            <th class="nine"></th>

        </tr>
        <c:forEach var="tour" items="${tours}">
            <tr>
                <td>${tour.city.country.name}</td>
                <td>${tour.city.name}</td>
                <td>${tour.description}</td>
                <td>${tour.days}</td>
                <td>${tour.hotel.name}</td>
                <td>${tour.hotel.description}</td>
                <td>${tour.hotel.starsNumber}</td>
                <td>${tour.price}</td>
                <td><input type="radio" class="radio" name="tourId" value="${tour.id}"/></td>
            </tr>

        </c:forEach>
    </table>
    </form>

</div>

</body>
</html>
