<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<%@ taglib uri="/WEB-INF/tld/localization.tld" prefix="l" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/registration.css"/>"/>
</head>
<body>
<jsp:include page="header.jsp"/>

<aside>
    <form name="registration" method="post" action="/servlet">
        <input type="hidden" name="command" value="registration">

        <label class="login5"> <l:localization key="label.login"/>
        </label>
        <input type="text" class="login6" name="login" value="">

        <label class="name"> <l:localization key="label.name"/>
        </label>
        <input type="text" class="name1" name="name" value="">

        <label class="surname"> <l:localization key="label.surname"/>
        </label>
        <input type="text" class="surname1" name="surname" value="">

        <label class="patronymic">Отчество</label>
        <l:localization key="label.patronymic"/>
        <input type="text" class="patronymic1" name="patronymic" value="">

        <label class="year">Дата рождения</label>
        <l:localization key="label.year"/>
        <input type="date" class="year1" name="year" value="">

        <label class="city">Город</label>
        <l:localization key="label.city"/>
        <input type="text" class="city1" name="city" value="">

        <label class="mobile">Телефон</label>
        <l:localization key="label.mobile"/>
        <input type="text" class="mobile1" name="mobile" value="">

        <label class="email">E-mail</label>
        <l:localization key="label.email"/>
        <input type="text" class="email1" name="email" value="">

        <label class="pass">Пароль</label>
        <l:localization key="label.password"/>
        <input type="password" class="pass1" name="password" value="">

        <label class="rpassword">Повторите пароль</label>
        <l:localization key="label.rpassword"/>
        <input type="password" class="rpassword1" name="rpassword" value="">


        <input type="submit" class="regist" name="registration" value="Регистрация"> <l:localization
            key="submit.registration"/>
    </form>
</aside>


</body>
</html>
