<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 01/06/2016
  Time: 04:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%!
    private static String toString(long timeInterval)
    {
        if (timeInterval < 1_000)
            return "Menos de un segundo";
        else if (timeInterval < 60_000)
            return (timeInterval/1_000)+" seconds";

        return "about " + (timeInterval/60_000) + " minutes";
    }
%>
<%
    int numberOfSessions = (Integer)request.getAttribute("numberOfSessions");
    @SuppressWarnings("unchecked")
    List<HttpSession> sessions =
            (List<HttpSession>)request.getAttribute("sessionList");
%>
<a href="<tag:url value="/login?logout"/>">Logout</a>
<h2>Sessions</h2>
<h3>Numero de Sessiones <%= numberOfSessions %> Activadas en esta aplicacion</h3><br />
<%
    long timestamp = System.currentTimeMillis();
    for(HttpSession aSession : sessions)
    {
        out.print(aSession.getId() + " - " +
                aSession.getAttribute("userName"));
        if(aSession.getId().equals(session.getId()))
            out.print(" (usted)");
        out.print(" - activo en " +
                toString(timestamp - aSession.getLastAccessedTime()));
        out.println(" <br />");
    }
%>

<%@ include file="/WEB-INF/jsp/baseFooter.jsp"  %>
