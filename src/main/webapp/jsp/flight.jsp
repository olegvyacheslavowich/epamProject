<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/localization.tld" prefix="l" %>
<html>
<head>
    <title>Данные о клиентах</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/flight.css"/>"/>

</head>

<body>

<jsp:include page="header.jsp"/>

<c:if test="${user eq null}">
    <jsp:forward page="login.jsp"/>
</c:if>

<form name="flight" method="post" action="/servlet">
    <input type="hidden" name="command" value="flight">
    <div class="first">
        <label class="to"> Туда: <br>
            Рейс: ${flightTo.plane.name} <br>
            Вылет из: ${flightTo.plane.departureCity.name} <br>
            Прилет в: ${flightTo.plane.arrivalCity.name}<br>
            Дата: ${flightTo.date} <br>
            Время вылета: ${flightTo.plane.departureTime}<br>
            Время в пути: ${flightTo.plane.travelTime}<br>
        </label>
    </div>

    <div class="second">
        <label class="from">Обратно: <br>
            Рейс: ${flightFrom.plane.name} <br>
            Вылет из: ${flightFrom.plane.departureCity.name} <br>
            Прилет в: ${flightFrom.plane.arrivalCity.name}<br>
            Дата: ${flightFrom.date} <br>
            Время вылета: ${flightFrom.plane.departureTime}<br>
            Время в пути: ${flightFrom.plane.travelTime}<br>
        </label>
    </div>


    <table class="table2">
        <tr>
            <th class="four">№</th>
            <th class="one">ФИО</th>
            <th class="one">Дата рождения</th>
            <th class="two">Документ</th>
            <th class="three">Номер документа</th>
            <th class="four">Телефон</th>
            <th class="four">e-mail</th>
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

    <input type="submit" name="choose" value="Перейти к оплате" class="choose2">

</form>

</body>
</html>
