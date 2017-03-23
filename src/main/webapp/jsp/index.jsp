<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<body>
MainForm <br/>
<a href="/jsp/login.jsp">Log in</a> <br/>
<a href="/jsp/registration.jsp">Registration</a> <br/>
<form action="/servlet" method="POST">
    <input type="hidden" name="command" value="lang">
    <input type="submit" name="/jsp/index.jsp" value="Русский">
    <input type="submit" name="/jsp/index.jsp" value="English">
</form>
${login} <br/>
${password}

</body>
</html>
