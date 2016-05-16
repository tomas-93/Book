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
    <label>Primera Configuracion</label>
    <%=
        application.getInitParameter("Primera-Configuracion")
    %><br>
    <label>Segunda Configuracion</label>
    <%=
        application.getInitParameter("Segunda-Configuracion")
    %><br>
</body>
</html>
