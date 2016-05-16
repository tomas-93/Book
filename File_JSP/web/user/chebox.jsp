<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 11/05/2016
  Time: 08:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Practica Submit</h1>
    <form action="/user/cheboxsubmit.jsp" method="POST">
        Selecciona la fruta que te gusta comer:<br />
        <input type="checkbox" name="fruit" value="Banana" /> Banana<br />
        <input type="checkbox" name="fruit" value="Apple" /> Apple<br />
        <input type="checkbox" name="fruit" value="Orange" /> Orange<br />
        <input type="checkbox" name="fruit" value="Guava" /> Guava<br />
        <input type="checkbox" name="fruit" value="Kiwi" /> Kiwi<br />
        <input type="submit" value="Submit" />
    </form>
</body>
</html>

