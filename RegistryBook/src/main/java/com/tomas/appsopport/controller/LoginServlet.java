package com.tomas.appsopport.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tomas on 30/05/2016.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login", loadOnStartup = 1)
public class LoginServlet extends HttpServlet
{
     private static final Map<String, String> USER_DATABASE_USER = new LinkedHashMap<>();
     static
     {
          USER_DATABASE_USER.put("tomas@gmail.com", "_yussef93");
          USER_DATABASE_USER.put("tomas@gmail.com", "_yussef93");
          USER_DATABASE_USER.put("tomas@gmail.com", "_yussef93");
          USER_DATABASE_USER.put("tomas@gmail.com", "_yussef93");
          USER_DATABASE_USER.put("tomas@gmail.com", "yussef");
     }
     @Override
     public void doGet(HttpServletRequest request, HttpServletResponse response)
             throws IOException, ServletException
     {
          request.getRequestDispatcher("/WEB-INF/content/book/login.jsp")
                  .forward(request, response);
     }
     @Override
     public void doPost(HttpServletRequest request, HttpServletResponse response)
             throws IOException, ServletException
     {
          HttpSession httpSession = request.getSession();
          if (httpSession.getAttribute("userName") != null)
          {
               response.sendRedirect("books");
               return;
          }
          String user = request.getParameter("email"),
                 password = request.getParameter("password");
          if (user == null && password == null && !USER_DATABASE_USER.containsKey(user)
                  && !password.equals(USER_DATABASE_USER.get(password)))
          {
               request.setAttribute("loginFail", true);
               request.getRequestDispatcher("/WEB-INF/content/book/login.jsp")
                       .forward(request, response);
          }else
          {
               httpSession.setAttribute("userName", user);
               request.changeSessionId();
               response.sendRedirect("books");
          }
     }
}
