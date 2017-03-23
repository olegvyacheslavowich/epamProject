<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form name="registration" method="post" action="/servlet">
    <input type="hidden" name="command" value="registration">
    Login <br/>
    <input type="text" name="login" value=""> <br/>
    Name <br/>
    <input type="text" name="name" value=""><br/>
    Surname <br/>
    <input type="text" name="surname" value=""><br/>
    Patronymic (Отчество) <br/>
    <input type="text" name="patronymic" value=""><br/>
    Year <br/>
    <input type="date" name="year" value=""><br/>
    City <br/>
    <input type="text" name="city" value=""><br/>
    Mobile <br/>
    <input type="text" name="mobile" value=""><br/>
    E-mail <br/>
    <input type="text" name="email" value=""><br/>
    Password <br/>
    <input type="password" name="password" value=""><br/>
    Repeat password <br/>
    <input type="password" name="rpassword" value=""><br/>
    <input type="submit" name="registration" value="<%=session.getValue("main.title")%>">
</form>

<form name="registration" method="GET" action="/servlet">
    <input type="hidden" name="command" value="/jsp/registration.jsp"/>
    <input type="submit" name="lang" value="Русский"/>
    <input type="submit" name="lang" value="English"/>
</form>

${exception}

</body>
</html>
