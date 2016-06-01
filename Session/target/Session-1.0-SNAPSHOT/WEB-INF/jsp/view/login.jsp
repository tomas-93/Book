<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 31/05/2016
  Time: 10:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<form action="login" method="post" class="card-panel" style="margin: 5% auto; width: 45%;">
    <h3 class="center">Login</h3>
    <input type="hidden" name="action" value="session">
    <div class="input-field col s12">
        <input type="email" name="userName" id="email">
        <label for="email">Nombre de Usuario</label>
    </div>
    <div class="input-field col s12">
        <input type="password" name="password" id="password">
        <label for="password">Contraseña</label>
    </div>
    <input type="submit" value="Enviar" class="btn waves-effect waves-light light-blue"/>
</form>


<%@ include file="/WEB-INF/jsp/baseFooter.jsp"  %>
