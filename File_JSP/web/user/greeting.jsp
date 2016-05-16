<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 11/05/2016
  Time: 08:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%!private static final String DEFAULT_USER = "USER_DEFAULT";%>
    <%
        String user = request.getParameter("user");

        if (user == null)
            user = DEFAULT_USER;
    %>
    <h1>Hola <%= user%>!</h1>
    <form action="/user/greeting.jsp" method="post">
        Ingresa tu nombre:<br />
        <input type="text" name="user" /><br />
        <input type="submit" value="Submit" />
    </form>
</body>
</html>
