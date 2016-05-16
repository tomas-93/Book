<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 11/05/2016
  Time: 08:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <% String [] array = request.getParameterValues("fruit"); %>
    <%
        if (array == null)
            out.println("No se selecciono ni una fruta");
        else
        {
            out.println("<ul>");
            for (String value: array)
            {
                out.println("<li>");
                out.println(value);
                out.println("</li>");
            }
            out.println("</ul>");
        }
    %>
</body>
</html>
