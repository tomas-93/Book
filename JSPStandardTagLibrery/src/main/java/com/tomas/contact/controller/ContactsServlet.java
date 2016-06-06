package com.tomas.contact.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tomas on 06/06/2016.
 */
@WebServlet(name = "ContactServlet", urlPatterns = "/contact-list", loadOnStartup = 1)
public class ContactsServlet extends HttpServlet
{
     @Override
     public void doGet(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException
     {

     }
     @Override
     public void doPost(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException
     {

     }
}
