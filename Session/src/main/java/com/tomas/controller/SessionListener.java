package com.tomas.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tomas on 31/05/2016.
 */
@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionIdListener
{
     private SimpleDateFormat formatter =
             new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");

     /**
      * <h1>Session creada</h1>
      * <p>Identifica la sessiones creadas</p>
      * @param e variable de evento lasando por el listener
      */
     @Override
     public void sessionCreated(HttpSessionEvent e)
     {
          System.out.println(this.date() + ": Session " + e.getSession().getId() +
                  " created.");
          SessionRegistry.addSession(e.getSession());
     }

     /**
      * <h1>Session destruida</h1>
      * <p>Identifica la sessiones destruida</p>
      * @param e variable de evento lasando por el listener
      */
     @Override
     public void sessionDestroyed(HttpSessionEvent e)
     {
          System.out.println(this.date() + ": Session " + e.getSession().getId() +
                  " destroyed.");
          SessionRegistry.removeSession(e.getSession());
     }

     /**
      * <h1>Session cambiada</h1>
      * <p>Identifica la sessiones cambiadas</p>
      * @param e variable de evento lasando por el listener
      */
     @Override
     public void sessionIdChanged(HttpSessionEvent e, String oldSessionId)
     {
          System.out.println(this.date() + ": Session ID " + oldSessionId +
                  " changed to " + e.getSession().getId());
          SessionRegistry.updateSessionId(e.getSession(), oldSessionId);
     }

     /**
      * <h1>Fecha</h1>
      * <p>Devuelve la fecha actual</p>
      * @return fecha actual
      */
     private String date()
     {
          return this.formatter.format(new Date());
     }
}
