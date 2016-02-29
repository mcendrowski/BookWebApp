/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.controller;

import edu.wctc.mrc.bookwebapp.model.AuthorService;
import edu.wctc.mrc.bookwebapp.model.MockAuthorDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author mcendrowski
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

 // db config
 private String driverClass;
 private String url;
 private String userName;
 private String password;
 
 private String modeValue;
    
    
@Inject
private AuthorService authService;

private static final String RESULT_PAGE = "/viewAllAuthors.jsp";
private static final String ACTION_PARAM="action";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

@Override
public void init() throws ServletException{
    driverClass = getServletContext().getInitParameter("db.driver.class");
    url = getServletContext().getInitParameter("db.url");
    userName = getServletContext().getInitParameter("db.username");
    password = getServletContext().getInitParameter("db.password");
    
}

  private void updatemodeValue(HttpServletRequest request) {
        String modeValue = request.getParameter("modeValue");
        this.modeValue = modeValue;      

    }
  
   private void updateRequest(HttpServletRequest request) {
        request.setAttribute("modeValue", this.modeValue);
   }
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        configDbConnection();
        
        String destination = RESULT_PAGE;
        String action = request.getParameter(ACTION_PARAM);
//        modeValue="READ";
        
        try  {
            /* TODO output your page here. You may use following sample code. */
//         MockAuthorDao ns = new MockAuthorDao();
//         AuthorService ns = new AuthorService();
    
//        if(action.equalsIgnoreCase("addEdit")){
//           if (modeValue.equalsIgnoreCase("EDIT")){
//               modeValue="READ";
//           }
//           else{
//               modeValue="EDIT";
//           }
////            request.setAttribute("modeValue", "ALLOW EDIT");
//            updateRequest(request);
//        }
//        else {
//            modeValue="READ";
//            updateRequest(request);
//        }


         request.setAttribute("authorList", authService.getAuthorList());
         
         String execute = request.getParameter("execute");
         
         if(execute.equalsIgnoreCase("set read mode")){
             this.modeValue="READ";
             
         }
         if(execute.equalsIgnoreCase("switch mode")){
             if (this.modeValue.equalsIgnoreCase("READ")){
                 this.modeValue="EDIT";
             }
             else{
                 this.modeValue="READ";
             }
         }         
         
         request.setAttribute("modeValue", this.modeValue);
        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage());
        }
         RequestDispatcher view
                = request.getRequestDispatcher(RESULT_PAGE);
        view.forward(request, response);
    }
    
    private void configDbConnection(){
        authService.getDao().initDao(driverClass, url, userName, password);
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
        processRequest(request, response);
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
