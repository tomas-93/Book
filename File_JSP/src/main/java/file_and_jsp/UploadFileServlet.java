package file_and_jsp;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tomas on 07/05/2016.
 */
@WebServlet(
        name="UploadFileServlet",
        urlPatterns = {"/files"},
        loadOnStartup = 1
)
@MultipartConfig(
        fileSizeThreshold = 5_242_880, //5MB
        maxFileSize = 20_971_520L, //20MB
        maxRequestSize = 41_943_040L //40MB
        )
public class UploadFileServlet extends HttpServlet
{
    private volatile int NUMBER_THE_FILE = 1;
    private Map<Integer, File> fileDataBaseMap = new LinkedHashMap<>();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");
        if (action == null)
            action = "list";

        switch(action)
        {
            case "create":
                this.showFileForm(request, response);
                break;
            case "view":
                this.viewFile(request, response);
                break;
            case "download":
                this.downloadAttachment(request, response);
                break;
            case "list":
                default:
                    this.listFiles(request, response);
                    break;
        }
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String action = request.getParameter("action");
        if (action == null)
            this.listFiles(request, response);

        if (action.equals("create"))
            this.createFile(request, response);
    }

    private void createFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        File file = new File();
        file.setCustomerName(request.getParameter("customerName"));
        file.setSubject(request.getParameter("subject"));
        file.setBody(request.getParameter("body"));

        Part filePart = request.getPart("file1");
        if (filePart == null && filePart.getSize() > 0)
        {
            System.out.println("fILE PART NO ES NULL");

            Attachments attachments = this.processAttachment(filePart);
            if(attachments != null )
            {
                System.out.println("No es nullo");
                file.addAttachment(attachments);
            }else
                System.out.println("es nullo attachments");

        }else
            System.out.println("ES nullo");


        int id;
        synchronized (this)
        {
            id= this.NUMBER_THE_FILE++;
            this.fileDataBaseMap.put(id, file);
        }
        response.sendRedirect("files?action=view&fileId="+id);

    }

    private Attachments processAttachment(Part filePart) throws IOException
    {
        InputStream inputStream = filePart.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int read;
        final byte[] bytes = new byte[1024];
        while((read = inputStream.read(bytes)) != -1)
        {
            byteArrayOutputStream.write(bytes, 0, read);
        }
        Attachments attachments = new Attachments();
        attachments.setName(filePart.getSubmittedFileName());
        attachments.setContents(byteArrayOutputStream.toByteArray());
        return attachments;
    }


    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String idString = request.getParameter("fileId");
        File ticket = this.getTicket(idString, response);
        if(ticket == null)
            return;

        String name = request.getParameter("attachments");
        if(name == null)
        {
            response.sendRedirect("files?action=view&fileId=" + idString);
            return;
        }

        Attachments attachments = ticket.getAttachment(name);
        if(attachments == null)
        {
            response.sendRedirect("files?action=view&fileId=" + idString);
            return;
        }

        response.setHeader("Content-Disposition",
                "attachments; filename=" + attachments.getName());
        response.setContentType("application/octet-stream");

        ServletOutputStream stream = response.getOutputStream();
        stream.write(attachments.getContents());
    }

    private File getTicket(String idString, HttpServletResponse response) throws IOException {
        if(idString == null || idString.length() == 0)
        {
            response.sendRedirect("files");
            return null;
        }

        try
        {
            File file = this.fileDataBaseMap.get(Integer.parseInt(idString));
            if(file == null)
            {
                response.sendRedirect("files");
                return null;
            }
            return file;
        }
        catch(Exception e)
        {
            response.sendRedirect("files");
            return null;
        }
    }

    private void viewFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String idString = request.getParameter("fileId");
        File file = this.getTicket(idString, response);
        if(file == null)
            return;

        request.setAttribute("fileId", idString);
        request.setAttribute("file", file);

        request.getRequestDispatcher("/file-support-2/jsp/viewFile.jsp")
                .forward(request, response);
    }

    private void showFileForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("/file-support-2/jsp/fileForm.jsp")
                .forward(request, response);
    }

    private void listFiles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("fileDataBaseMap", this.fileDataBaseMap);

        request.getRequestDispatcher("/file-support-2/jsp/listFile.jsp").forward(request, response);
    }
}
