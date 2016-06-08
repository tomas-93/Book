package com.tomas.contact.controller;

import com.tomas.contact.model.Contact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.time.Instant;
import java.time.Month;
import java.time.MonthDay;
import java.util.Collections;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Tomas on 06/06/2016.
 */
@WebServlet(name = "ContactServlet", urlPatterns = "/contact-list", loadOnStartup = 1)
public class ContactsServlet extends HttpServlet
{
     private static SortedSet<Contact> listContact = new TreeSet<>();
     static
     {
          listContact.add(new Contact("Jane", "Sanders", "555-1593", "394 E 22nd Ave",
                  MonthDay.of(Month.JANUARY, 5),
                  Instant.parse("2013-02-01T21:22:23Z")
          ));
          listContact.add(new Contact( "John", "Smith", "555-0712", "315 Maple St",
                  null, Instant.parse("2012-10-15T15:31:17Z")
          ));
          listContact.add(new Contact("Scott", "Johnson", "555-9834", "424 Oak Dr",
                  MonthDay.of(Month.NOVEMBER, 17),
                  Instant.parse("2013-04-05T01:45:01Z")
          ));     }
     @Override
     public void doGet(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException
     {
          String language = request.getParameter("leng");
          if (language != null)
          {
               if("fra".equalsIgnoreCase(language))
                    Config.set(request, Config.FMT_LOCALE, Locale.FRANCE);
               else if("en".equalsIgnoreCase(language))
                    Config.set(request, Config.FMT_LOCALE, Locale.US);

          }


          if(request.getParameter("empty") != null)
               request.setAttribute("contacts", Collections.<Contact>emptySet());
          else if (request.getParameter("url2") != null )
          {
               request.setAttribute("listContact", listContact);
               request.getRequestDispatcher("/WEB-INF/jsp/view/listContactTwo.jsp")
                       .forward(request, response);
          }else
          {
               request.setAttribute("listContact", listContact);
               request.getRequestDispatcher("/WEB-INF/jsp/view/listContacts.jsp")
                       .forward(request, response);
          }

     }

}
