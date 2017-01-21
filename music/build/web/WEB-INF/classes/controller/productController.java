/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.ProductDB;
import database.UserDB;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javabeans.CartProduct;
import javabeans.LineItem;
import javabeans.User;
import javabeans.product;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author sony
 */
@WebServlet(name = "productController", urlPatterns = {"/productController"})
public class productController extends HttpServlet {

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
            out.println("<title>Servlet productController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet productController at " + request.getContextPath() + "</h1>");
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
        
        HttpSession session = request.getSession();
        
        int CustomerOrAdmin = (int)session.getAttribute("customerOradmin"); // CustomerOrAdmin == 0 - Customer, CustomerOrAdmin == 1 - Admin
        String url = "/productlist.jsp";
        String action = request.getParameter("action");

        //String actioncart = request.getParameter("productController");
        if (action == null) {
            action = "productController";  // default action
        }
        if(action != null && action.equals("login")){
        
            action = "showProducts";
        }

        if(action != null && action.equals("showProducts")){     //to show list of products in product list page
        List<product> products = new ArrayList<>();
        products = ProductDB.getProducts();
        request.setAttribute("products", products);
        request.setAttribute("CustomerOrAdmin", CustomerOrAdmin);
        request.getRequestDispatcher("productlist.jsp").forward(request, response);
        }
        else if(action != null && action.equals("productController"))
        {
        ServletContext sc = getServletContext();
        url = "/cart.jsp";
        // get current action
        //action = request.getParameter("action");
        //if (action == null) {
          //  action = "cartController";  // default action
        //}
        
        //if (action.equals("cartController")) {
            String productID = request.getParameter("productid");
            String quantityString = request.getParameter("quantity");

            product cartData = ProductDB.getProduct(productID);
            CartProduct Cart = (CartProduct) session.getAttribute("cartData");
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
        //}
        //else if (action.equals("checkout")) {
          //  url = "/checkout.jsp";
        //}
        sc.getRequestDispatcher(url)
                .forward(request, response);
        }
        else if(action != null && action.equals("Update")){  //this part gets executed when an admin updates a product
        
            String productID = request.getParameter("productid");
            product cartDataToDelete = ProductDB.getProduct(productID);
            request.setAttribute("cartDataToDelete", cartDataToDelete);
            url = "/updateProduct.jsp";
            
            ServletContext sc = getServletContext();
            
                    sc.getRequestDispatcher(url)
                .forward(request, response);
        }
        else if(action != null && action.equals("Submit")){
            double checkPrice = 0;
            int i = 0;
            String productID = request.getParameter("productid");
            String productStatus = request.getParameter("productstatus") ;
            String productprice = request.getParameter("productprice");
            String image = "guitar.jpg";
          /**  try{
            ServletContext x = this.getServletContext();
            String path = x.getRealPath("/images");
            System.out.println(path + "path");
            DiskFileUpload p = new DiskFileUpload();
            List q = p.parseRequest(request);
            Iterator z = q.iterator();
            while (z.hasNext()){
            
                FileItem f = (FileItem)
                z.next();
                if(f.isFormField() == false){
                
                    String filename = f.getName();
                    if(filename != null && filename.length() > 0){
                    
                        File g = new File(filename);
                        filename = g.getName();
                        
                        //making a unique file name
                        long w = System.currentTimeMillis();
                        filename = w + filename;
                        System.out.println("path" + path + " file " + filename);
                        //upload file
                        File t = new File(path, filename);
                        f.write(t);
                        if(f.getFieldName().equals("image")){
                        image = filename;
                        }
                    }
                }
            }
            
            } catch(Exception e){
                i = 1;
                e.printStackTrace();
            }
            */
           try {
            checkPrice = Double.parseDouble(productprice);  //to check the price has only integers
            int integerplaces = productprice.indexOf('.');
            
            if(integerplaces != -1)
            {
            int decimalPlace = productprice.length() - integerplaces - 1;
            if(decimalPlace > 0)
            {
                i = 1;
            String productid = request.getParameter("productid");
            product cartDataToDelete = ProductDB.getProduct(productid);
            request.setAttribute("message", "Please enter an integer value Price column.");
            request.setAttribute("cartDataToDelete", cartDataToDelete);
            url = "/updateProduct.jsp";
            request.getRequestDispatcher(url).forward(request, response);
            }
            }

             }
            catch (NumberFormatException e) {
                i = 1;
            String productid = request.getParameter("productid");
            product cartDataToDelete = ProductDB.getProduct(productid);
            request.setAttribute("message", "Please enter a valid number in price text box");
            request.setAttribute("cartDataToDelete", cartDataToDelete);
            url = "/updateProduct.jsp";
            request.getRequestDispatcher(url).forward(request, response);
            
            }

            if(i != 1){
            ProductDB.updateProduct(productID, productStatus, productprice);
            
            List<product> products = new ArrayList<>();
            products = ProductDB.getProducts();
            request.setAttribute("products", products);
            request.setAttribute("CustomerOrAdmin", CustomerOrAdmin);
            request.getRequestDispatcher("productlist.jsp").forward(request, response);
            }
        }
        else if(action != null && action.equals("Go Back")){
        List<product> products = new ArrayList<>();
        products = ProductDB.getProducts();
        request.setAttribute("products", products);
        request.setAttribute("CustomerOrAdmin", CustomerOrAdmin);
        request.getRequestDispatcher("productlist.jsp").forward(request, response);
        }
        else if(action != null && action.equals("Delete")){
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete Product", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        String productID = request.getParameter("productid");
        ProductDB.deleteProduct(productID);
        List<product> products = new ArrayList<>();
        products = ProductDB.getProducts();
        request.setAttribute("products", products);
        request.setAttribute("CustomerOrAdmin", CustomerOrAdmin);
        request.getRequestDispatcher("productlist.jsp").forward(request, response);
        }
        else {
        List<product> products = new ArrayList<>();
        products = ProductDB.getProducts();
        request.setAttribute("products", products);
        request.setAttribute("CustomerOrAdmin", CustomerOrAdmin);
        request.getRequestDispatcher("productlist.jsp").forward(request, response);
        }
        }
        else if(action != null && action.equals("Add Product")){

            ServletContext sc = getServletContext();
            request.setAttribute("CustomerOrAdmin", CustomerOrAdmin);
            url = "/addProduct.jsp";
          
                    sc.getRequestDispatcher(url)
                .forward(request, response);
        }
        else if(action != null && action.equals("Add")){ //to add a new product by admin
            double checkPrice = 0;
            int i = 0;
            product Product = new product();
            Product.setproductname(request.getParameter("productName"));
            Product.setprice(request.getParameter("productPrice"));
            String productStatusToAdd = request.getParameter("productstatus");
            
            try {
            checkPrice = Double.parseDouble(request.getParameter("productPrice"));
            int integerplaces = request.getParameter("productPrice").indexOf('.');
            
            if(integerplaces != -1)
            {
            int decimalPlace = request.getParameter("productPrice").length() - integerplaces - 1;
            if(decimalPlace > 0)
            {
                i = 1;
            request.setAttribute("message", "Please enter a valid integer in price column");
            ServletContext sc = getServletContext();
            request.setAttribute("CustomerOrAdmin", CustomerOrAdmin);
            url = "/addProduct.jsp";
          
                    sc.getRequestDispatcher(url)
                .forward(request, response);
            }
            }

             }
            catch (NumberFormatException e) {
                i = 1;
            request.setAttribute("message", "Please enter a valid number in price text box");
            ServletContext sc = getServletContext();
            request.setAttribute("CustomerOrAdmin", CustomerOrAdmin);
            url = "/addProduct.jsp";
          
                    sc.getRequestDispatcher(url)
                .forward(request, response);
            
            }
            if(i != 1){
            if(productStatusToAdd.equalsIgnoreCase("1")){
            productStatusToAdd = "Available";
            }
            else{
            
                productStatusToAdd = "Out of stock";
            }
            Product.setproductstatus(productStatusToAdd);
            Product.setimageurl("images/guitar1.jpg");
            Product.setproductcode("Default Code");
            Product.setdescription("Default description");
            ProductDB.insertProduct(Product);
            
        List<product> products = new ArrayList<>();
        products = ProductDB.getProducts();
        request.setAttribute("products", products);
        request.setAttribute("CustomerOrAdmin", CustomerOrAdmin);
        request.getRequestDispatcher("productlist.jsp").forward(request, response);
        }
            
        }
        else if(action != null && action.equals("Assign Role to Users")){
        
            ServletContext sc = getServletContext();
            List<User> user = new ArrayList<>();
            user = UserDB.getUsers();
            request.setAttribute("user", user);
            request.setAttribute("CustomerOrAdmin", CustomerOrAdmin);
            
            url = "/assignRoletoUsers.jsp";
          
                    sc.getRequestDispatcher(url)
                .forward(request, response);
        }
        else if(action != null && action.equals("Change the role")){
        
            ServletContext sc = getServletContext();
            User user = new User();
            user.setEmail(request.getParameter("useremail"));
            user.setUserType(request.getParameter("userstatus"));
            UserDB.updateUserType(user);
            
            List<User> user1 = new ArrayList<>();
            user1 = UserDB.getUsers();
            request.setAttribute("user", user1);
            request.setAttribute("CustomerOrAdmin", CustomerOrAdmin);
        url = "/assignRoletoUsers.jsp";
          
                    sc.getRequestDispatcher(url)
                .forward(request, response);
        }
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
 
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
