package com.tomas.controller;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Tomas on 31/05/2016.
 */

public class SessionRegistry
{

     private static final Map<String, HttpSession> SESSION_DATABASE = new Hashtable<>();


     private SessionRegistry()
     {

     }

     /**<h1>Agregar Session</h1>
      * <p>Agrega las sessiones que resibe como parametro a un base de datos en memoria.</p>
      * @param session objeto HttpSession.
      */
     public static void addSession(HttpSession session)
     {
          SESSION_DATABASE.put(session.getId(), session);
     }

     /**
      * <h1>Actualizacion de Sessiones</h1>
      * <p>Atualiza una session que se encuentra en la base de datos</p>
      * @param session objeto HttpSession que suplira al viejo objeto
      * @param oldSession es un String que representa el id de la secion que se actualizara
      */
     public static void updateSessionId(HttpSession session, String oldSession)
     {
          synchronized (SESSION_DATABASE)
          {
               SESSION_DATABASE.remove(oldSession);
               addSession(session);
          }
     }

     /**
      * <h1>Elimina una session especifica</h1>
      * <p>Elimina la session que se le pasa como para metro.</p>
      * @param session objeto HttpSession el cual representa el objeto que se eliminara de la base de datos
      */
     public static void removeSession(HttpSession session)
     {
          SESSION_DATABASE.remove(session.getId());
     }

     /**
      * <h1>Obtiene todas las sessiones</h1>
      * <p>Devuelve una lista con el contenido de la base de datos</p>
      * @return devuelve un List<HttpSession> con el contenido de la base de datos
      */
     public static List<HttpSession> getAllSessions()
     {
          return new ArrayList<>(SESSION_DATABASE.values());
     }

     /**
      * <h1>Numero de Sessiones</h1>
      * <p>Devuelve la cantidad de sessiones que se encuentran disponibles en la base de datos</p>
      * @return un numero entero que representa el numero de sessiones
      */
     public static int getNumberOfSessions()
     {
          return SESSION_DATABASE.size();
     }



}
