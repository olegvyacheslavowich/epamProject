<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title_login}</title>
</head>
<body>

<form name="login" method="POST" action="/servlet">
    <input type="hidden" name="command" value="login"/>
    Login <br/>
    <input type="text" name="login" value=""/> <br/>
    Password <br/>
    <input type="password" name="password" value=""/> <br/>
    <input type="submit" value="${submit_login}"/>
</form>

<form name="login" method="GET" action="/servlet">
    <input type="hidden" name="command" value="/jsp/login.jsp"/>
    <input type="submit" name="lang" value="Русский"/>
    <input type="submit" name="lang" value="English"/>
</form>


${incorrectLoginOrPassword}

</body>
</html>
