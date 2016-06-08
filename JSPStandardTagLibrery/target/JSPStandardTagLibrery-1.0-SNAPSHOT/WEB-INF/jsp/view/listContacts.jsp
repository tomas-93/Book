<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 07/06/2016
  Time: 05:15 PM
  To change this template use File | Settings | File Templates.
--%>

<h1 class="center">Lista de Contactos</h1>
<br>
<div class="center">
    <a href="<core:url value="/contact-list">
            <core:param name="url2" value="url2"/>
            <core:param name="leng" value="es"/>
         </core:url>" class="center" style="margin: 0 5px 0 5px;">Lista Dos Español</a>
    <a href="<core:url value="/contact-list">
            <core:param name="url2" value="url2"/>
            <core:param name="leng" value="en"/>
         </core:url>" class="center" style="margin: 0 5px 0 5px;">Lista Dos Ingles</a>
    <a href="<core:url value="/contact-list">
            <core:param name="url2" value="url2"/>
            <core:param name="leng" value="fra"/>
         </core:url>" class="center" style="margin: 0 5px 0 5px;">Lista Dos Frances</a>
    <a href="<core:url value="/contact-list" var="prueba" scope="request">
         </core:url>" class="center" style="margin: 0 5px 0 5px;">Prueba de URL</a>
</div>

<core:choose>
    <core:when test="${funcion:length(listContact)==0}">
        <i>No hay contactos</i>
    </core:when>
    <core:otherwise>
        <core:forEach items="${listContact}" var="contact">
            <div class="card" style="margin: 1% auto; width: 30%; padding: 1%;">
                <b>
                    <core:out value="${contact.lastName}, ${contact.firstName}" />
                </b><br />
                <core:out value="${contact.address}" /><br />
                <core:out value="${contact.phoneNumber}" /><br />
                <core:if test="${contact.birthday != null}">
                    Cumpleaños: ${contact.birthday}<br />
                </core:if>
                Creado: ${contact.dateCreated}<br /><br />
            </div>
        </core:forEach>
    </core:otherwise>
</core:choose>

<%@ include file="/WEB-INF/jsp/baseFooter.jsp"  %>
