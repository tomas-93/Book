<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 07/06/2016
  Time: 11:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1 class="center"><fmt:message key="title.browser" /></h1>
<h2 class="center"><fmt:message key="title.page" /></h2>
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
</div>
<core:choose>
    <core:when test="${funcion:length(listContact) == 0}">
        <i><fmt:message key="message.noContacts" /></i>
    </core:when>
    <core:otherwise>
        <core:forEach items="${listContact}" var="contact">
            <div class="card" style="margin: 1% auto; width: 30%; padding: 1%;">
                <b>
                    <c:out value="${contact.lastName}, ${contact.firstName}" />
                </b><br />
                <core:out value="${contact.address}" /><br />
                <core:out value="${contact.phoneNumber}" /><br />
                <core:if test="${contact.birthday != null}">
                    <fmt:message key="label.birthday" />:
                    ${contact.birthday.month.getDisplayName(
                            'FULL', pageContext.response.locale
                        )}&nbsp;${contact.birthday.dayOfMonth}<br />

                </core:if>
                <fmt:message key="label.creationDate" />:
                <fmt:formatDate value="${contact.oldDateCreated}" type="both"
                                dateStyle="long" timeStyle="long" />
                <br /><br />
            </div>
        </core:forEach>
    </core:otherwise>
</core:choose>


<%@ include file="/WEB-INF/jsp/baseFooter.jsp"  %>
