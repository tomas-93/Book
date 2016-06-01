<%@ page import="com.tomas.appsopport.model.Book" %>
<form action="books" method="post" class="card-panel" style="margin: 1% auto; width: 85%;"
      enctype="multipart/form-data">
    <h4 class="center" style="margin: 1%;">Formulario</h4>
    <div class="input-field col s12">
        <% if ((boolean)request.getAttribute("flagEdit")) {%>
        <input id="title" type="text" name="title" value="<%= ((Book)request.getAttribute("book")).getTitle() %>" />
        <label for="title">Titulo del Libro</label>
        <% }else{ %>
        <input id="title" type="text" name="title" />
        <label for="title">Titulo del Libro</label>
        <% } %>
    </div>
    <div class="input-field col s12">
        <% if ((boolean)request.getAttribute("flagEdit")) {%>
        <input id="author" type="text" name="author" value="<%= ((Book)request.getAttribute("book")).getAuthor() %>" />
        <% }else{ %>
        <input id="author" type="text" name="author"/>
        <% } %>
        <label for="author">Autor del Libro</label>

    </div>
    <div class="input-field col s12">
        <% if ((boolean)request.getAttribute("flagEdit")) {%>
        <input id="editorial" type="text" name="editorial" value="<%= ((Book)request.getAttribute("book")).getEditorial() %>" />
        <% }else{ %>
        <input id="editorial" type="text" name="editorial"/>
        <% } %>
        <label for="editorial">Editorial del Libro</label>

    </div>
        <div class="input-field col s12">


            <textarea id="description" name="description" class="materialize-textarea">
            </textarea>
            <label for="description">Descripcion del Libro</label>


        </div>
    <div class="input-field col s12">
        <% if ((boolean)request.getAttribute("flagEdit")) {%>
        <input id="date" type="date" class="datepicker" name="date" value="<%= ((Book)request.getAttribute("book")).getDate() %>" />
        <% }else{ %>
        <input id="date" type="date" class="datepicker" name="date"/>
        <% } %>
        <label for="date" >Fecha</label>
    </div>
    <div class="file-field input-field">
        <div class="btn light-blue">
            <span>Libro</span>
            <input type="file" name="file">
        </div>
        <div class="file-path-wrapper">
            <input class="file-path validate" type="text" placeholder="Seleccionar Libro">
        </div>
    </div>
    <input type="hidden" name="action" value="create"/>
    <input type="submit" value="Enviar" class="btn waves-effect waves-light light-blue"/>
</form>

<%@include file="/WEB-INF/content/baseFooter.jsp"%>
<% if ((boolean)request.getAttribute("flagEdit")) {
    request.setAttribute("flagEdit", false);

%>
<script> $("#description").val  ("<%= ((Book)request.getAttribute("book")).getDescription() %>")</script>
<% }%>