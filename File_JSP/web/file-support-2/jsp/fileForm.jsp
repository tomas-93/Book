
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2>Create a Ticket</h2>
<form method="POST" action="files" enctype="multipart/form-data">
    <input type="hidden" name="action" value="create"/>
    Your Name<br/>
    <input type="text" name="customerName"><br/><br/>
    Subject<br/>
    <input type="text" name="subject"><br/><br/>
    Body<br/>
    <textarea name="body" rows="5" cols="30"></textarea><br/><br/>
    <b>Attachments</b><br/>
    <input type="file" name="file1"/><br/><br/>
    <input type="submit" value="Submit"/>
</form>

<%@
include file="/file-support-2/baseFooter.jsp"
%>
