<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.tomas.appsopport.model.Book" %>
<h1 id="title">Lista de libros</h1>
<%
    @SuppressWarnings("unchecked")
    Map<Integer, Book> bookDataBase = (Map<Integer, Book>) request.getAttribute("books");
    if(bookDataBase.size() > 0)
    {//start if
        for (int id: bookDataBase.keySet())
        {//start for
            Book book = bookDataBase.get(id);
%>
            <div class ="card-panel" style="float: left; margin: 2%; float: left;">
                <h1><%= "#" + book.getIdBook() + " " + book.getTitle() %></h1>
                <h2>Autor: <%= book.getAuthor() %></h2>
                <h3>Editorial: <%= book.getEditorial() %></h3>
                <h4>Descripcion: <%= book.getDescription() %></h4>
                <h5>Fecha: <%= book.getDate() %></h5>
                <div style="width: 100%;">
                    <a href="<tag:url value="/books">
                                <tag:param name="action" value="delete"/>
                                <tag:param name="idBook" value="<%= String.valueOf(book.getIdBook()) %>"/>
                             </tag:url>" class="btn waves-effect waves-light light-blue">Eliminar</a>
                    <a href="<tag:url value="/books">
                                <tag:param name="action" value="edit"/>
                                <tag:param name="idBook" value="<%= String.valueOf(book.getIdBook()) %>"/>
                             </tag:url>" class="btn waves-effect waves-light light-blue">Editar</a>
                    <a href="<tag:url value="/books">
                                <tag:param name="action" value="show"/>
                                <tag:param name="idBook" value="<%= String.valueOf(book.getIdBook()) %>"/>
                             </tag:url>" class="btn waves-effect waves-light light-blue">Ver</a>

                </div>
            </div>
<%
        }//end for
    }//end if
    else {//start elsee
%>
        <h3 style="float: left; margin: 0 1% 0 1%;">Sin libros</h3>
        <a href="<tag:url value="/books">
                    <tag:param name="action" value="add"/>
                 </tag:url>"
           class="waves-effect waves-light btn light-blue"
           style="margin: 5px 0 0 1%;">Agregar libro</a>
<%  }//end else
%>

<%@
 include file="/WEB-INF/content/baseFooter.jsp"
%>
<%
    String flag = (String)request.getAttribute("okDelete");
    if (flag != null && flag.equals("ok"))
    {
%>
    <script>Materialize.toast('Se elimino el elemento de la base de datos', 3000, 'rounded')</script>
<% } %>

