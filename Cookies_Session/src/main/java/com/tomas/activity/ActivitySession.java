package com.tomas.activity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

/**
 * Created by Tomas on 18/05/2016.
 */

@WebServlet(
        name = "ActivityServlet",
        urlPatterns = "/do/*"
)

public class ActivitySession extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        this.recordSessionActivity(request);

        this.viewSessionActivity(request, response);
    }

    private void viewSessionActivity(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/jsp/p2/viewSessionActivity.jsp")
                .forward(request, response);
    }


    private void recordSessionActivity(HttpServletRequest request) throws UnknownHostException
    {
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("activity") == null)
            httpSession.setAttribute("activity", new Vector<PageVisit>());
        @SuppressWarnings("unchecked")
        Vector<PageVisit> visits = (Vector<PageVisit>) httpSession.getAttribute("activity");
        if (!visits.isEmpty())
        {
            PageVisit last = visits.lastElement();
            last.setLeftTimesTamp(System.currentTimeMillis());
        }

        PageVisit now = new PageVisit();
        now.setEnteredTimesTamp(System.currentTimeMillis());

        if (request.getQueryString() == null)
            now.setRequest(request.getRequestURL().toString());
        else
            now.setRequest(request.getRequestURL()+"?"+request.getQueryString());

        now.setIpAddress(InetAddress.getByName(request.getRemoteAddr()));

        visits.add(now);

    }

}
