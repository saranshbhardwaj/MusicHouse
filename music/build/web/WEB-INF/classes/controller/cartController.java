/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.ProductDB;
import java.io.IOException;
import java.io.PrintWriter;
import javabeans.CartProduct;
import javabeans.LineItem;
import javabeans.product;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sony
 */
@WebServlet(name = "cartController", urlPatterns = {"/cartController"})
public class cartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet cartController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet cartController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext sc = getServletContext();
        String url = "/cart.jsp";
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "cartController";  // default action
        }
        
        if (action.equals("cartController")) {
            String productCode = request.getParameter("productCode");
            String quantityString = request.getParameter("quantity");
            
            HttpSession session = request.getSession();

            product cartData = ProductDB.getProduct(productCode);
            CartProduct Cart = (CartProduct) session.getAttribute("Cart");
            if(Cart  == null){
            
                Cart = new CartProduct();
            }
            
            //if the user enters a negative or invalid quantity,
            //the quantity is automatically reset to 1.
            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) {
                    quantity = 1;
                }
            } catch (NumberFormatException e) {
                quantity = 1;
            }
            
            LineItem lineItem = new LineItem();
            lineItem.setProduct(cartData);
            lineItem.setQuantity(quantity);
            if(quantity > 0)
            {
            Cart.addItem(lineItem);
            } else if(quantity == 0){
            Cart.removeItem(lineItem);
            }
            session.setAttribute("cartData", Cart);
            url = "/cart.jsp";
        }
        else if (action.equals("checkout")) {
            url = "/checkout.jsp";
        }
        sc.getRequestDispatcher(url)
                .forward(request, response);
    }

    /**

     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
