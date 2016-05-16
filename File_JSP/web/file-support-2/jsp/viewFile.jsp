<%@ page import="file_and_jsp.File" %>
<%@ page import="file_and_jsp.Attachments" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 14/05/2016
  Time: 09:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@
        include file="/file-support-2/baseHeader.jsp"
%>
<%
    String fileId = (String)request.getAttribute("fileId");
    File file= (File)request.getAttribute("file");
%>

<h2>File #<%= fileId %>: <%= file.getSubject() %></h2>
<i>Nombre del Archivo - <%= file.getCustomerName() %></i><br />
<%= file.getBody() %><br />

Archivo adjunto:
<%
    int i = 0;
    for(Attachments a : file.getAttachments())
    {
        if(i++ > 0)
            out.print(", ");
%><a href="<c:url value="/files">
                        <c:param name="action" value="download" />
                        <c:param name="fileId" value="<%= fileId %>" />
                        <c:param name="attachments" value="<%= a.getName() %>" />
                    </c:url>"><%= a.getName() %> - Descargar</a><%
    }
%><br />
<a href="<c:url value="/files" />">Return to list tickets</a>
<%@
        include file="/file-support-2/baseFooter.jsp"
%>

