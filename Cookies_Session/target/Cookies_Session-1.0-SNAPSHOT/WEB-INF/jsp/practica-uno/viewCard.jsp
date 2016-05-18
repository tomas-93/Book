<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 18/05/2016
  Time: 06:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/header.jsp"%>
<h2>Vista de la carta</h2>
<a href="/list/card" >Lista de Productos</a><br>
<a href="/shop?action=remove">Remover Productos</a><br>
<a href="/shop?action=browser">Create</a><br>


<%
    @SuppressWarnings("unchecked")
    Map<Integer, String> product = (Map<Integer, String>) request.getAttribute("products");
    @SuppressWarnings("unchecked")
    Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");


    if (cart == null || cart.size()==0)
        out.println("Tu carta esta vacia");
    else
    {
        for(int id: product.keySet())
            out.println(product.get(id) + "(Numero: " + cart.get(id) + ") <br>");
    }

 %>

<%@include file="/WEB-INF/jsp/footer.jsp"%>
