package file;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tomas on 29/04/2016.
 */

@WebServlet(name = "Servlet", urlPatterns = "/file", loadOnStartup = 1)
@MultipartConfig( fileSizeThreshold = 5_242_880, maxFileSize = 20_971_520L, maxRequestSize = 41_943_040L)

public class UploadFileMainServlet extends HttpServlet
{
    private volatile int File_ID_SQUENCE = 1;
    private Map<Integer, Ticket> ticketDataBase = new LinkedHashMap<>();

    @Override
    public void doGet(HttpServletRequest mRequest, HttpServletResponse mResponse) throws ServletException, IOException
    {
        String action = mRequest.getParameter("action");

        if (action == null)
            action = "list";


        switch (action)
        {
            case "create":
                this.showTicketFrom(mResponse);
                break;
            case "view":
                this.viewTicket(mRequest, mResponse);
                break;
            case "download":
                this.downloadAttachment(mRequest, mResponse);
                break;
            default:
                this.listTickets(mResponse);
                break;
        }

    }

    @Override
    public void doPost(HttpServletRequest mRequest, HttpServletResponse mResponse)
        throws ServletException, IOException
    {
        String action = mRequest.getParameter("action");
        if (action == null)
            action = "list";
        switch (action)
        {
            case "create":
                this.createTicket(mRequest, mResponse);
                break;
            case "list":
            default:
                mResponse.sendRedirect("tickets");
                break;

        }
    }

    private void createTicket(HttpServletRequest mRequest, HttpServletResponse mResponse)
        throws ServletException, IOException
    {
        Ticket ticket = new Ticket();
        ticket.setCustomerName(mRequest.getParameter("customerName"));
        ticket.setSubject(mRequest.getParameter("subject"));
        ticket.setBody(mRequest.getParameter("body"));

        Part filePart  = mRequest.getPart("file1");
        if (filePart != null && filePart.getSize()>0)
        {
            Attachment attachment = this.processAttachment(filePart);
            if (attachment != null)
                ticket.addAttachment(attachment);

        }

        int id;
        synchronized (this)
        {
            id = this.File_ID_SQUENCE++;
            this.ticketDataBase.put(id, ticket);
        }
        mResponse.sendRedirect("file?action=view&ticketId=" + id);
    }

    private Attachment processAttachment(Part filePart)
            throws IOException
    {
        InputStream inputStream = filePart.getInputStream();
        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
        int read;
        final byte [] bytes = new byte[1024];
        while ((read = inputStream.read(bytes)) != -1)
        {
            byteArrayInputStream.write(bytes, 0, read);
        }

        Attachment attachment = new Attachment();
        attachment.setName(filePart.getSubmittedFileName());
        attachment.setContents(byteArrayInputStream.toByteArray());

        return attachment;
    }

    private void listTickets(HttpServletResponse mResponse)
            throws IOException, ServletException
    {
        PrintWriter writer = this.writeHeader(mResponse);
        writer.append("<h2>Archivos</h2>\r\n");
        writer.append("<a href=\"file?action=create\">Subir Archivo")
                .append("</a><br/><br/>\r\n");

        if(this.ticketDataBase.size() == 0)
            writer.append("<i>No hay documentos en la base de datos.</i>\r\n");
        else
        {
            for(int id : this.ticketDataBase.keySet())
            {
                String idString = Integer.toString(id);
                Ticket ticket = this.ticketDataBase.get(id);
                writer.append("Archivo #").append(idString)
                        .append(": <a href=\"file?action=view&ticketId=")
                        .append(idString).append("\">").append(ticket.getSubject())
                        .append("</a> (Documento: ").append(ticket.getCustomerName())
                        .append(")<br/>\r\n");
            }
        }

        this.writeFooter(writer);
    }

    private void downloadAttachment(HttpServletRequest mRequest, HttpServletResponse mResponse)
            throws IOException
    {
        String idString = mRequest.getParameter("ticketId");
        Ticket ticket = this.getTicket(idString, mResponse);
        if(ticket == null)
            return;

        String name = mRequest.getParameter("attachment");
        if(name == null)
        {
            mResponse.sendRedirect("file?action=view&ticketId=" + idString);
            return;
        }

        Attachment attachment = ticket.getAttachmenMap(name);
        if(attachment == null)
        {
            mResponse.sendRedirect("file?action=view&ticketId=" + idString);
            return;
        }

        mResponse.setHeader("Content-Disposition",
                "attachment; filename=" + attachment.getName());
        mResponse.setContentType("application/octet-stream");

        ServletOutputStream stream = mResponse.getOutputStream();
        stream.write(attachment.getContents());
    }

    private Ticket getTicket(String idString, HttpServletResponse mResponse)
            throws IOException
    {
        if(idString == null || idString.length() == 0)
        {
            mResponse.sendRedirect("tickets");
            return null;
        }

        try
        {
            Ticket ticket = this.ticketDataBase.get(Integer.parseInt(idString));
            if(ticket == null)
            {
                mResponse.sendRedirect("tickets");
                return null;
            }
            return ticket;
        }
        catch(Exception e)
        {
            mResponse.sendRedirect("tickets");
            return null;
        }
    }



    private void viewTicket(HttpServletRequest mRequest, HttpServletResponse mResponse)
        throws IOException, ServletException
    {
        String idString = mRequest.getParameter("ticketId");
        Ticket ticket = this.getTicket(idString, mResponse);
        if(ticket == null)
            return;

        PrintWriter writer = this.writeHeader(mResponse);

        writer.append("<h2>Archivo #").append(idString)
                .append(": ").append(ticket.getSubject()).append("</h2>\r\n");
        writer.append("<i>Nombre del documento - ").append(ticket.getCustomerName())
                .append("<br/>\r\n");
        writer.append("Descripcion - ")
                .append(ticket.getBody()).append("<br/>\r\n");

        if(ticket.getNumberOfAttachments() > 0)
        {
            writer.append("Documento: ");
            int i = 0;
            for(Attachment attachment : ticket.getArrachmentsMap())
            {
                if(i++ > 0)
                    writer.append(", ");
                writer.append("<a href=\"file?action=download&ticketId=")
                        .append(idString).append("&attachment=")
                        .append(attachment.getName()).append("\">")
                        .append(attachment.getName()).append("</a>");
            }
            writer.append("</i><br/><br/>\r\n");
        }

        writer.append("<a href=\"file\">Volver a la lista de documentos.</a>\r\n");

        this.writeFooter(writer);
    }

    private void showTicketFrom(HttpServletResponse mResponse)
            throws ServletException, IOException
    {
        PrintWriter writer = this.writeHeader(mResponse);
        writer.append("<h2>Subir documento</h2>\r\n");
        writer.append("<form method=\"POST\" action=\"file\" ")
                .append("enctype=\"multipart/form-data\">\r\n");
        writer.append("<input type=\"hidden\" name=\"action\" ")
                .append("value=\"create\"/>\r\n");
        writer.append("Nombre<br/>\r\n");
        writer.append("<input type=\"text\" name=\"customerName\"/><br/><br/>\r\n");
        writer.append("Tema<br/>\r\n");
        writer.append("<input type=\"text\" name=\"subject\"/><br/><br/>\r\n");
        writer.append("Descripcion<br/>\r\n");
        writer.append("<textarea name=\"body\" rows=\"5\" cols=\"30\">")
                .append("</textarea><br/><br/>\r\n");
        writer.append("<b>Adjuntar documento</b><br/>\r\n");
        writer.append("<input type=\"file\" name=\"file1\"/><br/><br/>\r\n");
        writer.append("<input type=\"submit\" value=\"Submit\"/>\r\n");
        writer.append("</form>\r\n");
        
        this.writeFooter(writer);

    }

    private void writeFooter(PrintWriter writer)
    {
        writer.append("    </body>\r\n").append("</html>\r\n");
    }


    private PrintWriter writeHeader(HttpServletResponse mResponse)
        throws ServletException, IOException
    {
        mResponse.setContentType("text/html");
        mResponse.setCharacterEncoding("UTF-8");

        PrintWriter writer = mResponse.getWriter();

        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("    <head>\r\n")
                .append("        <title>Customer Support</title>\r\n")
                .append("    </head>\r\n")
                .append("    <body>\r\n");


        return writer;
    }

}
