<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/localization.tld" prefix="l" %>
<html>
<head>
    <title><l:localization key="label.private_office"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/admin.css"/>"/>
</head>
<body>
<jsp:include page="header.jsp"/>

<c:if test="${sessionScope.admin.account.login eq null}">
    <jsp:forward page="login.jsp"/>
</c:if>
<form name="clientData" method="post" action="/servlet">
    <input type="hidden" name="command" value="clientData">
    <div class="inform">
        <label class="informL">
            <l:localization key="label.login"/>: ${user.account.login} <br>
            <l:localization key="label.full_name"/>: ${user.fullName} <br>
            <l:localization key="label.birthday"/>: ${user.birthday}<br>
            <l:localization key="label.paper"/>: ${user.paper} <br>
            <l:localization key="label.paper_num"/>: ${user.documentNum}<br>
            <l:localization key="label.mobile"/>: ${user.phone}<br>
            <l:localization key="label.email"/>: ${user.email}<br>
        </label>
    </div>
</form>



<div class="stat">
    <label class="statL">
        <l:localization key="label.statistics"/>: <br>
        <l:localization key="label.all_tours"/>:${statistic.toursNumber}<br>
        <l:localization key="label.hot_tours"/>:${statistic.hotToursNumber}<br>
        <l:localization key="label.sold"/>: ${statistic.sold} <br>
    </label>
</div>

<label class="tours"><l:localization key="label.tours"/></label>
<form name="hotTours" method="post" action="/servlet">
    <input type="hidden" name="command" value="hotTour">
    <div class="blocktable4">
        <table>
            <tr>
                <th><l:localization key="label.country"/></th>
                <th><l:localization key="label.city"/>/th>
                <th><l:localization key="label.description"/></th>
                <th><l:localization key="label.days"/></th>
                <th><l:localization key="label.hotel"/></th>
                <th><l:localization key="label.about_hotel"/></th>
                <th><l:localization key="label.stars"/></th>
                <th><l:localization key="label.price"/></th>
                <th></th>
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
                    <c:if test="${tour.id eq -1}">
                        <td><l:localization key="label.hot_tour"/></td>
                    </c:if>
                    <c:if test="${tour.id ne -1}">
                        <td><input type="radio" name="tourId" value="${tour.id}"/></td>
                    </c:if>
                </tr>

            </c:forEach>
        </table>
    </div>
    <input type="submit" name="choose" class="ch" value="<l:localization key="label.make_hot"/>">
</form>
</body>
</html>
