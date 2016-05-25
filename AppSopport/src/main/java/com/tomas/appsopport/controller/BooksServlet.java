package com.tomas.appsopport.controller;

import com.tomas.appsopport.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tomas on 19/05/2016.
 */
@WebServlet(
        name = "BooksServlet",
        urlPatterns = {
                "/books",
        },
        loadOnStartup = 1)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 100,
        maxRequestSize = 1024 * 1024 * 200
)

public class BooksServlet extends HttpServlet
{
     private final Map<Integer, Book> booksDataBaseMap = new Hashtable<>();

     public BooksServlet()
     {

     }
     /**
      *  Metodo que responde a las peticiones Get
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException
      */
     @Override
     public void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException
     {
          response.setContentType("text/html");
          response.setCharacterEncoding("UTF-8");
          System.out.println("Get......... xD");
          String action = request.getParameter("action");
          if (action == null)
          {
               action = "list";
               LocalDate now = LocalDate.now();
               Book book = new Book();
               book.setTitle("Libro 1");
               book.setAuthor("Tomas Yussef");
               book.setEditorial("Itesco");
               book.setDescription("Descripción Libro 1");
               book.setIdBook(1);
               book.setDate(now.toString());

               this.booksDataBaseMap.put(book.getIdBook(), book);
               Book book1 = new Book();
               book1.setTitle("Libro 2");
               book1.setAuthor("Tomas Yussef");
               book1.setEditorial("Itesco");
               book1.setDescription(" Descripción Libro 2");
               book1.setIdBook(2);
               book1.setDate(now.toString());

               this.booksDataBaseMap.put(book1.getIdBook(), book1);
               Book book2 = new Book();
               book2.setTitle("Libro 3");
               book2.setAuthor("Tomas Yussef");
               book2.setEditorial("Itesco");
               book2.setDescription("Descripción Libro 3");
               book2.setIdBook(3);
               book2.setDate(now.toString());

               this.booksDataBaseMap.put(book2.getIdBook(), book2);
          }

          switch (action)
          {
               default:
               case "list":
                    this.listBooks(request, response);
                    break;

               case "show":
                    this.showBook(request, response);
                    break;

               case "add":
                    this.addBook(request, response);
                    break;
               case "edit":
                    this.editBook(request, response);
                    break;
               case "delete":
                    this.doDelete(request, response);
                    break;
          }
     }

     /**
      *   Metodo que responde a las peticiones POST
      * @param request
      * @param response
      * @throws IOException
      * @throws ServletException
      */
     @Override
     public void doPost(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException
     {
          String action = request.getParameter("action");
          if (action.equals("create"))
               this.createBook(request, response);
     }

     /**
      *   Metodo que responde al la peticion PUT
      * @param request
      * @param response
      * @throws IOException
      * @throws ServletException
      */
     @Override
     public void doPut(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException
     {
          System.out.println("Editado..............");
          this.putBook(request, response);
     }


     /**
      *   Metodo que responde a la peticion DELETE
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException
      */
     @Override
     public void doDelete(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException
     {
          System.out.println("Eliminando............");
          this.deleteBook(request, response);
     }

     /**
      *   Metodo privado que envia la una variable MAP que alamcena toda la informacion
      *   relacionado con los libros
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException
      */
     private void listBooks(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException
     {
          request.setAttribute("books", this.booksDataBaseMap);
          request.getRequestDispatcher("/WEB-INF/content/book/listBook.jsp")
                  .forward(request, response);
     }

     /**
      *   Metodo que envia un libro en especifico
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException
      */
     private void showBook(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException
     {
          String idBook = request.getParameter("idBook");
          if (idBook != null)
               request.setAttribute("book", this.booksDataBaseMap.get(Integer.parseInt(idBook)));
          request.getRequestDispatcher("/WEB-INF/content/book/showBook.jsp")
                  .forward(request, response);
     }

     /**
      *   Metodo que busca y elimina el libro de la variable MAP
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException
      */
     private void deleteBook(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException
     {
          int idBook = Integer.parseInt(request.getParameter("idBook"));
          this.booksDataBaseMap.remove(idBook);
          request.setAttribute("okDelete", "ok");
          this.listBooks(request, response);

     }

     /**
      *   Metodo que crea un objeto BOOK donde almacena toda la informacion recibida
      *   del formulario
      *
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException
      */
     private void createBook(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException
     {
          Book book = new Book();
          book.setTitle(request.getParameter("title"));
          book.setAuthor(request.getParameter("author"));
          book.setDate(request.getParameter("date"));
          book.setDescription(request.getParameter("description"));
          book.setEditorial(request.getParameter("editorial"));

          Part filePart = request.getPart("file");
          if (filePart.getSize() > 0)
          {
               book.setNameFile(filePart.getName());
               ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
               InputStream inputStream = filePart.getInputStream();
               int read;
               final byte[] BYTES = new byte[1024];
               while((read = inputStream.read(BYTES)) != -1)
               {
                    byteArrayOutputStream.write(BYTES, 0, read);
               }
               book.setFile(byteArrayOutputStream.toByteArray());
          }

          book.setIdBook(this.booksDataBaseMap.size() + 1);
          this.booksDataBaseMap.put(book.getIdBook(), book);
          request.setAttribute("book", book);
          this.showBook(request, response);
     }

     /**
      *   Metodo que redirecciona al formulario
      * @param request
      * @param response
      * @throws IOException
      * @throws ServletException
      */
     private void addBook(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException
     {
          request.getRequestDispatcher("/WEB-INF/content/book/formBook.jsp")
                  .forward(request, response);
     }

     /**
      *   Metodo que redirecciona al formulario con la informacion para editar.
      * @param request
      * @param response
      * @throws IOException
      * @throws ServletException
      */
     private void editBook(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException
     {
          request.setAttribute("flagEdit", true);
          System.out.println(Integer.parseInt(request.getParameter("idBook")));
          request.setAttribute("book", this.booksDataBaseMap.get(Integer.parseInt(request.getParameter("idBook"))));
          request.getRequestDispatcher("/WEB-INF/content/book/formBook.jsp")
                  .forward(request, response);

     }

     /**
      * Cambia los valores de objecto seleccionado en edit
      * @param request
      * @param response
      * @throws IOException
      * @throws ServletException
      */
     private void putBook(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException
     {
          Book book = new Book();
          book.setAuthor(request.getParameter("author"));
          book.setEditorial(request.getParameter("editorial"));
          book.setDescription(request.getParameter("description"));
          book.setTitle(request.getParameter("title"));
          book.setDate(request.getParameter("date"));

          int idBook = Integer.parseInt(request.getParameter("idBook"));

          this.booksDataBaseMap.remove(idBook);
          this.booksDataBaseMap.put(idBook, book);
     }
}
