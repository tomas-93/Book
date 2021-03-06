package com.tomas.controller;

import com.tomas.controller.url.SchemeRoutes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tomas on 31/05/2016.
 */
@WebServlet(name = "Session", urlPatterns = "/session", loadOnStartup = 1)
public class SessionListServlet extends HttpServlet
{
     @Override
     public void doGet(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException
     {
          if (request.getSession().getAttribute("userName") == null)
          {
               response.sendRedirect(SchemeRoutes.LOGIN);
               return;
          }
          request.setAttribute("numberOfSessions",
                  SessionRegistry.getNumberOfSessions());
          request.setAttribute("sessionList", SessionRegistry.getAllSessions());
          request.getRequestDispatcher(SchemeRoutes.JSP_SESSION)
                  .forward(request, response);
     }


}
