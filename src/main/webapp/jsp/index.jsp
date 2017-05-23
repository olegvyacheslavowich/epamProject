<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/localization.tld" prefix="l" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/index.css"/>"/>
</head>
<body>
<jsp:include page="header.jsp"/>

<aside>
    <form name="search" method="post" action="/servlet">
        <input type="hidden" name="command" value="search">

        <label class="cityname1"><l:localization key="label.departure_city"/></label>
        <input type="text" class="cityname2" name="departureCity" value="">

        <label class="countries"><l:localization key="label.country"/> </label>

        <select name="country" class="countries1">
            <option value="Любая"><l:localization key="country.any_country"/></option>
            <option value="Турция"><l:localization key="label.turkey"/></option>
            <option value="Россия">Россия</option>
            <option value="Казахстан">Казахстан</option>
            <option value="Украина">Украина</option>
            <option value="Германия">Германия</option>
        </select>

        <label class="hotelClass"><l:localization key="label.stars"/></label>
        <select name="hotelStars" class="hotelClass1">
            <option value="0"><l:localization key="hotel.any_class"/></option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>

        <label class="date5"><l:localization key="label.date"/></label>
        <input type="date" class="date6" name="date" value="">

        <label class="tourisrs"><l:localization key="label.tourist"/></label>
        <label class="adult"><l:localization key="label.adult"/>: </label>
        <select name="adultNumber" class="adult1">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>

        <label class="children"> <l:localization key="label.children"/>: </label>
        <select name="childrenNumber" class="children1">
            <option value="0">0</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>

        <input type="submit" class="search" name="search" value="<l:localization key="label.find"/>">
    </form>

</aside>
</body>
</html>
