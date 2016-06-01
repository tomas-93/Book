package com.tomas.controller;

import com.tomas.controller.url.SchemeRoutes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Tomas on 31/05/2016.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login", loadOnStartup = 1)
public class LoginServlet extends HttpServlet
{
     private static final Map<String, String>  USER_DATA_BASE = new Hashtable<>();
     static
     {
          USER_DATA_BASE.put("user1@user", "user");
          USER_DATA_BASE.put("user2@user", "user");
          USER_DATA_BASE.put("user3@user", "user");
          USER_DATA_BASE.put("user4@user", "user");
          USER_DATA_BASE.put("user5@user", "user");
     }
     @Override
     public void doGet(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException
     {
          HttpSession session = request.getSession();
          if (request.getParameter("logout") != null)
          {
               session.invalidate();
               response.sendRedirect(SchemeRoutes.LOGIN);
               return;
          }else if(session.getAttribute("userName") != null)
          {
               response.sendRedirect(SchemeRoutes.SESSION);
               return;
          }
          request.getRequestDispatcher(SchemeRoutes.JSP_LOGIN)
                  .forward(request,response);
     }
     @Override
     public void doPost(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException
     {
          HttpSession session = request.getSession();
          if (session.getAttribute("userName") != null)
          {
               response.sendRedirect(SchemeRoutes.LOGIN);
               return;
          }
          String userName = request.getParameter("userName"),
                 password = request.getParameter("password");

          if (userName == null || password == null
                  || !LoginServlet.USER_DATA_BASE.containsKey(userName)
                  || !password.equals(LoginServlet.USER_DATA_BASE.get(userName)))
          {
               request.setAttribute("loginFailed", true);
               request.getRequestDispatcher(SchemeRoutes.JSP_LOGIN)
                       .forward(request, response);
          }else
          {
               session.setAttribute("userName", userName);
               request.changeSessionId();
               response.sendRedirect(SchemeRoutes.SESSION);
          }
     }
}
