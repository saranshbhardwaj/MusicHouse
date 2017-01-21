/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.Emailserver;
import database.TempUserDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import database.UserDB;
import java.util.Date;
import java.util.UUID;
import javabeans.Reset;
import javabeans.TempUser;
import javabeans.User;
import util.Hashpassword;

/**
 *
 * @author sony
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {

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
            out.println("<title>Servlet UserController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserController at " + request.getContextPath() + "</h1>");
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
        
        String url = "/home.jsp";
        String action = request.getParameter("action"); //get the current action
        
        if (action == null || action.equals("") || action.isEmpty()) { //If action is null, enter home.jsp
            
            url = "/home.jsp";
        }
        //perform login action for customer and admin
        else if(action.equals("login"))
       {
                String email = request.getParameter("email"); //get request parameter
            String pwd = request.getParameter("password");
            String salt = UserDB.validateUser(email);
            
            if (salt != null) {
            
                if (UserDB.selectUser(email, pwd, salt)){
                
                    User user = UserDB.getUser(email);
                    String userType = user.getUserType();
                    if (userType.equalsIgnoreCase("Customer")) {
                    
                        session.setAttribute("theUser", user);
                        int customers = 0;//CustomerDB.getcustomers(user.getEmail());
                        session.setAttribute("cust", customers);
                        session.setAttribute("customerOradmin", 0); //setting session to  check whether user is a customer or admin
                        url = "/productController";
                        
                    }
                    else if (userType.equalsIgnoreCase("Admin")) {
                        session.setAttribute("theAdmin", user);
                        session.setAttribute("customerOradmin", 1);
                        url = "/productController";
                    }
                } else {
                    request.setAttribute("message", "Incorrect Username or Password.Please try again.");
                    url = "/login.jsp";
                }
                }else {
                request.setAttribute("message", "Incorrect Username or Password.Please try again.");
                url = "/login.jsp";
            } 
            }
        else if (action.equals("create")) {      //if user is creating a new account
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String cPassword = request.getParameter("confirm_password");
            String token=request.getParameter("token");
            if (UserDB.getUser(email) != null) {
                request.setAttribute("message", "The Email id is already present in the database");
                request.setAttribute("email", email);
                request.setAttribute("name", name);
                url = "/signup.jsp";
            } else if (!password.equals(cPassword)) {
                request.setAttribute("message", "Password does not match");
                request.setAttribute("email", email);
                request.setAttribute("name", name);
                url = "/signup.jsp";
            } else {
                TempUser tempuser = new TempUser();
                if(token=="") token = UUID.randomUUID().toString();
                //tempuser.setDate(new Date());
                tempuser.setEmail(email);
                String salt = Hashpassword.getSalt();
                password = Hashpassword.hashAndSaltPassword(password, salt);
                tempuser.setPassword(password);
                tempuser.setToken(token);
                tempuser.setSalt(salt);
                tempuser.setName(name);
                TempUserDB tempuserDB = new TempUserDB();
                tempuserDB.addTempUser(tempuser);
                String link = request.getRequestURL().toString();
                Emailserver.activateUser(name, email, token, link);
                request.setAttribute("message", "A link for activation has been send to your ID");
                url = "/login.jsp";
            }

        }
        else if (action.equals("activate")) {   // when user hits the link to activate account, this block get executed.
            String token = request.getParameter("token");
            System.out.println(token);
            if (TempUserDB.getTempUser(token) == null) {
                String msg = "Invalid Link";
                request.setAttribute("message", msg);
                url = "/signup.jsp";
            }
            else {
                TempUser tempuser = TempUserDB.getTempUser(token);
                String name = tempuser.getName();
                String email = tempuser.getEmail();
                String password = tempuser.getPassword();
                String salt = tempuser.getSalt();
                System.out.println(password);
                User user = new User(name, email, "Customer", password, salt, false);
                int customers = 0;//CustomerDB.getcustomers(user.getEmail());
                session.setAttribute("cust", customers);
                UserDB.addUser(user);
                TempUserDB.deleteUser(email);
                request.setAttribute("message", "User has been added. Please login");
                url = "/login.jsp";

            }

        }
        else if (action.equals("forgot")) {       //When user enters his/her email and submit for gorgot password
            String email = request.getParameter("email");
            if (UserDB.getUser(email) != null) {
                User user = UserDB.getUser(email);
                String token = UUID.randomUUID().toString();
                String link = request.getRequestURL().toString();
                Emailserver.resetPassword(user.getName(), email, token, link);
                Reset reset = new Reset(email, token, new Date());
                request.setAttribute("message", "An email has been sent to reset the password");
                UserDB.addUserforReset(reset);
                url = "/login.jsp";
            } else {
                request.setAttribute("message", "Invalid username, please try again");
                url = "/login.jsp";
            }
        } else if (action.equals("reset")) {
            String token = request.getParameter("token");
            System.out.println(token);
            if (UserDB.getUserforReset(token) == null) {
                String message = "Invalid Link";
                request.setAttribute("message", message);
                url = "/login.jsp";
            } else {
                Reset reset = UserDB.getUserforReset(token);
                String email = reset.getEmail();
                session.setAttribute("email", email);
                url = "/updatepass.jsp";

            }

        } else if (action.equals("updatepass")) {       //WHen user enters new password to reset the old one
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String cPassword = request.getParameter("confirm_password");
            if (!password.equals(cPassword)) {
                request.setAttribute("message", "Password does not match");
                url = "/updatepass.jsp";
            } else {
                User user=UserDB.getUser(email);
                UserDB.updatePassword(email, password,user.getSalt());
                UserDB.deleteUserforReset(email);
                request.setAttribute("message", "Password updated successfully. You can login");
                url = "/login.jsp";
            }
        }
        else if (action.equals("logout")) {
            if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
                session.invalidate();
            }
            url = "/home.jsp";
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
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
