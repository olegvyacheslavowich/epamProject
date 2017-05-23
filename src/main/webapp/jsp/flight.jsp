<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/localization.tld" prefix="l" %>
<html>
<head>
    <title><l:localization key="label.clients_data"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/flight.css"/>"/>

</head>

<body>

<jsp:include page="header.jsp"/>

<c:if test="${sessionScope.tour eq null}">
    <jsp:forward page="index.jsp"/>
</c:if>

<form name="flight" method="post" action="/servlet">
    <input type="hidden" name="command" value="flight">
    <div class="first">
        <label class="to"><l:localization key="label.to"/>: <br>
            <l:localization key="label.flight"/>: ${flightTo.plane.name} <br>
            <l:localization key="label.departure"/>: ${flightTo.plane.departureCity.name} <br>
            <l:localization key="label.arrival"/>: ${flightTo.plane.arrivalCity.name}<br>
            <l:localization key="label.departure_date"/>: ${flightTo.date} <br>
            <l:localization key="label.departure_time"/>: ${flightTo.plane.departureTime}<br>
            <l:localization key="label.travel_time"/>: ${flightTo.plane.travelTime}<br>
        </label>
    </div>

    <div class="second">
        <label class="from"><l:localization key="label.from"/>: <br>
            <l:localization key="label.flight"/>: ${flightFrom.plane.name} <br>
            <l:localization key="label.departure"/>: ${flightFrom.plane.departureCity.name} <br>
            <l:localization key="label.arrival"/>: ${flightFrom.plane.arrivalCity.name}<br>
            <l:localization key="label.departure_date"/>: ${flightFrom.date} <br>
            <l:localization key="label.departure_time"/>: ${flightFrom.plane.departureTime}<br>
            <l:localization key="label.travel_time"/>: ${flightFrom.plane.travelTime}<br>
        </label>
    </div>


    <table class="table2">
        <tr>
            <th class="four">№</th>
            <th class="one"><l:localization key="label.full_name"/></th>
            <th class="one"><l:localization key="label.birthday"/></th>
            <th class="two"><l:localization key="label.paper"/></th>
            <th class="three"><l:localization key="label.paper_num"/></th>
            <th class="four"><l:localization key="label.mobile"/></th>
            <th class="four"><l:localization key="label.email"/></th>
        </tr>
        <c:forEach var="i" begin="1" end="${adultNumber}">


            <tr>
                <td>${i}</td>
                <td><input type="text" name="fullName${i}" value=""></td>
                <td><input type="date" name="birthday${i}" value=""></td>
                <td><select name="paper${i}" class="paper">
                    <option value="passport">Паспорт</option>
                    <option value="identityСard">Удостоверение личности</option>
                    <option value="birthСertificate">Свидетельство о рождении</option>
                </select></td>
                <td><input type="text" name="documentNum${i}" value=""></td>
                <td><input type="text" name="phone${i}" value=""></td>
                <td><input type="text" name="email${i}" value=""></td>
            </tr>

        </c:forEach>
    </table>

    <input type="submit" name="choose" value="<l:localization key="label.to_pay"/>" class="choose2">

</form>

</body>
</html>
