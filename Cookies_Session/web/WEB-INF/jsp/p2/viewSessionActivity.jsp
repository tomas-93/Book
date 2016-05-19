<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.tomas.activity.PageVisit" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 18/05/2016
  Time: 06:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@
    include file="/WEB-INF/jsp/header.jsp"
%>

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
    SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyy HH:mm:ss Z");
 %>
<h2>Session Properties</h2>
Session ID: <%= session.getId() %><br />
Session is new: <%= session.isNew() %><br />
Session created: <%= format.format(new Date(session.getCreationTime()))%><br />

<h2>Page Activity This Session</h2>
<%
    @SuppressWarnings("unchecked")
    Vector<PageVisit> visits =
            (Vector<PageVisit>)session.getAttribute("activity");

    for(PageVisit visit : visits)
    {
        out.print(visit.getRequest());
        if(visit.getIpAddress() != null)
            out.print(" IP " + visit.getIpAddress().getHostAddress());
        out.print(" (" + format.format(new Date(visit.getEnteredTimesTamp())));
        if(visit.getLeftTimesTamp() != null)
        {
            out.print(", Alojado por " + toString(
                    visit.getLeftTimesTamp() - visit.getEnteredTimesTamp()
            ));
        }
        out.println(")<br />");
    }
%>



<%@
    include file="/WEB-INF/jsp/footer.jsp"
%>