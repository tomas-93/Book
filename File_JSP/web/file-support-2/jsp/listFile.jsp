<%@ page import="java.util.Map" %>
<%@ page import="file_and_jsp.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 14/05/2016
  Time: 09:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@
        include file="/file-support-2/baseHeader.jsp"
%>
<%
    @SuppressWarnings("unchecked")
    Map<Integer, File> fileDataBase = (Map<Integer, File>) request.getAttribute("fileDataBaseMap");
%>
<h2>Archivos</h2>
<a href="<c:url value="/files">
            <c:param name="action" value="create"/>
            </c:url>">
    Create File
</a><br>

<%
    if (fileDataBase.size() == 0)
    {
%><i>No hay archivos en el sistemas</i><%
}else
{
    for(int id : fileDataBase.keySet())
    {
        String idString = Integer.toString(id);
        File file = fileDataBase.get(id);
%>File #<%= idString %>: <a href="<c:url value="/files">
                        <c:param name="action" value="view" />
                        <c:param name="fileId" value="<%= idString %>" />
                    </c:url>"><%= file.getSubject() %></a> (customer:
<%= file.getCustomerName() %>)<br /><%
        }
    }
%>


<%@
        include file="/file-support-2/baseFooter.jsp"
%>

