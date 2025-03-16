/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class MainServlet extends HttpServlet {
    //set fixed "action" name declared in the jsp/html form
    public final String SIGN_IN = "signin";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Initialize default action and url
            /*
            IMPORTANT - Set "action" in jsp by setting [name="action"] and value="..." in the <input> tag for everything 
                        that require servlet's process (search things, login, fetch data,...)
            */
            String action = request.getParameter("action");
            
            //replace "" with "error.jsp" when all exception has been fixed
            String url = "";
            
            try {
                //default redirect to sign-in page if no action has been recorded
                if (action==null) {
                    action = SIGN_IN;
                }
                //for debugging purposes
                System.out.println("[MainServlet.java] action: " + action);
                
                switch (action) {
                    case SIGN_IN: {
                        url = "Login/index.jsp";
                        break;
                    }
                    
                    /*
                        get login target via dropdown list choice then redirect
                        the user based on choice
                    */
                    case "Login": {
                        String target = request.getParameter("target");
                        switch(target) {
                            case "customer": {
                                url="/loginCustServlet";
                                break;
                            }
                            
                            case "sales": {
                                url="/loginSalesServlet";
                                break;
                            }
                            
                            case "mechanic": {
                                url="/loginMechanicServlet";
                                break;
                            }
                        }
                        
                        break;
                    }
                    
                    //remove session then redirect to login page
                    case "logout": {
                        HttpSession session = request.getSession();
                        session.invalidate();
                        url="Login/index.jsp";
                        break;
                    }
                    
                    //CustomerFunction-related action
                    case "getCustList": {
                        url="/GetCustomerServlet";
                        break;
                    }
                    
                    case "custSearch": {
                        url="/GetCustomerServlet";
                        break;
                    }
                    
                    case "custAdd": {
                        url="/AddCustomerServlet";
                        break;
                    }
                    
                    case "custView": {
                        url="/CustomerDetailsServlet";
                        break;
                    }
                    
                    case "custDel": {
                        url="/DeleteCustomerServlet";
                        break;
                    }
                    
                    case "custEdit": {
                        url="/EditCustomerServlet";
                        break;
                    }
                    // SalesPerson
                    /*case "getReport": {
                        url="/ReportServlet";
                        break;
                    }
                    case "getPart":{
                        url="SalesDashboard/PartServlet";
                        break;
                    }*/

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //Perform system state integrity check before proceeding.
                if ("validateState".equals(action)) url="Login/systemState.jsp";
                // Render auxiliary view for specific user action.
                if("renderView".equals(action)) url="Login/renderView.jsp";
                System.out.println("[MainServlet.java] assigned url: " + url);
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
