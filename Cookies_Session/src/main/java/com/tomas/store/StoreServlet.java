package com.tomas.store;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

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
 * Created by Tomas on 16/05/2016.
 */

@WebServlet(
        name = "StoreServlet",
        urlPatterns = {"/create/card", "/view/card", "/list/card", "/remove/card","/shop"},
        loadOnStartup = 1
)

public class StoreServlet extends HttpServlet
{
    private Map<Integer, String> product = new Hashtable<>();

    public StoreServlet()
    {
        this.product.put(1, "Sandpaper");
        this.product.put(2, "Nails");
        this.product.put(3, "Glue");
        this.product.put(4, "Paint");
        this.product.put(5, "Tape");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String action = request.getParameter("action");
        if (action == null)
            action = "list";

        switch (action)
        {
            case "create":
                this.createToCard(request, response);
                break;
            case "browser":
                this.browserCard(request, response);
                break;
            case "remove":
                this.removeCard(request, response);
                break;
            case "list":
                this.listCard(request, response);
                break;
            default:
                this.listCard(request, response);
                break;
        }

    }

    private void listCard(HttpServletRequest request, HttpServletResponse response)
        throws  IOException, ServletException
    {
        request.setAttribute("products", this.product);
        request.getRequestDispatcher("/WEB-INF/jsp/practica-uno/viewCard.jsp")
                .forward(request, response);
    }

    private void removeCard(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        request.getSession().removeAttribute("cart");
        response.sendRedirect("shop?action=viewCart");
    }

    private void browserCard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setAttribute("products", this.product);
        request.getRequestDispatcher("/WEB-INF/jsp/practica-uno/browserCard.jsp")
                .forward(request, response);
    }

    private void createToCard(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        int productId;
        try
        {
            productId = Integer.parseInt(request.getParameter("productId"));
        }catch (Exception e)
        {
            response.sendRedirect("shop");
            return;
        }
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("cart") == null)
                httpSession.setAttribute("cart", new Hashtable<Integer, Integer>());

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cart = (Map<Integer, Integer>) httpSession.getAttribute("cart");

        if (!cart.containsKey(productId))
            cart.put(productId, 0);
        cart.put(productId, cart.get(productId) + 1);

        for (int id: cart.keySet())
            System.out.println(id);

        response.sendRedirect("shop?action=list");
    }

}
