<%@ page import="com.tomas.appsopport.model.Book" %>

<%
    Book book = (Book) request.getAttribute("book");
%>
<div class ="card-panel center" style="margin: 1% auto; padding: 1%; width: auto;">
    <h1><%= "#" + book.getIdBook() + " " + book.getTitle() %></h1>
    <h2>Autor: <%= book.getAuthor() %></h2>
    <h3>Editorial: <%= book.getEditorial() %></h3>
    <h4>Descripcion: <%= book.getDescription() %></h4>
    <h5>Fecha: <%= book.getDate() %></h5>
</div>

<%@ include file="/WEB-INF/content/baseFooter.jsp" %>