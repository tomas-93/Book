<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 18/05/2016
  Time: 06:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@
    include file="/WEB-INF/jsp/header.jsp"
%>
<h2>Productos</h2>
<% 
    @SuppressWarnings("unchecked")
    Map<Integer, String> product = (Map<Integer, String>) request.getAttribute("products");
    for (int id: product.keySet())
    {
%>
    <a href="<tag:url value="/shop">
        <tag:param name="action" value="create"/>
        <tag:param name="productId" value="<%= Integer.toString(id) %>" />
        </tag:url>">
    <%= product.get(id)%>
    </a>
<br>

<%
    }
%>

<%@
    include file="/WEB-INF/jsp/footer.jsp"
%>
