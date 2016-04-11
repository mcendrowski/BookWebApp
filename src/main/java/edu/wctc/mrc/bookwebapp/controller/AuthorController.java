/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.controller;

//import edu.wctc.mrc.bookwebapp.ejb.AuthorFacade;

import edu.wctc.mrc.bookwebapp.entity.Author;
import edu.wctc.mrc.bookwebapp.service.AuthorService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author mcendrowski
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    // db config
//    private String driverClass;
//    private String url;
//    private String userName;
//    private String password;
//
//    private String modeValue;
//    private String dbJndiName;

//    @Inject
//    private AuthorService authService;
    
//     @Inject
//    private AuthorFacade authService;
     
      // DO THIS INSTEAD (see init() method):
    private AuthorService authService;

    private static final String RESULT_PAGE = "/viewAllAuthors.jsp";
    private static final String ACTION_PARAM = "action";
    private static final String EXECUTE_UPDATE = "update";
    private static final String EXECUTE_INSERT = "insert";
    private static final String EXECUTE_DELETE = "delete";
    private static final String EXECUTE_SWITCH_MODE = "switch mode";
    private static final String EXECUTE_SET_READ_MODE = "set read mode";
    private static final String UPDATE_PAGE = "/updateDetails.jsp";
    private static final String UPDATE_AUTHOR_ID_PARAM = "update_author_id";
    private static final String AUTHOR_ID_PARAM = "author_id";
    private static final String UPDATED_RECORD_PARAM = "updated_record";
    private static final String UPDATED_AUTHOR_ID = "updated_author_id";
    private static final String AUTHOR_LIST = "authorList";
     private static final String START_PAGE = "/index.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    public void init() throws ServletException {
////        driverClass = getServletContext().getInitParameter("db.driver.class");
////        url = getServletContext().getInitParameter("db.url");
////        userName = getServletContext().getInitParameter("db.username");
////        password = getServletContext().getInitParameter("db.password");
//dbJndiName = getServletContext().getInitParameter("db.jndi.name");
//
//    }

//    private void setColorAttribute(HttpServletRequest request) {
//
//        String defineColor = request.getParameter("color");
//
//        String colorAttribute = "";
//        if (defineColor.equalsIgnoreCase("normal")) {
//            colorAttribute = "black";
//        } else {
//            colorAttribute = "red";
//        }
//
//        request.setAttribute("color", colorAttribute);
//    }
//    private void setUpdateAttributesOld(HttpServletRequest request) throws ClassNotFoundException, SQLException {
//
//        Integer authorId = Integer.parseInt(request.getParameter("update_author_id"));
////        String authorId = request.getParameter("update_author_id");
//        
//        request.setAttribute("author_id", authorId);
//        
////        request.setAttribute("updated_record", authService.getAuthorById(authorId));        
//        request.setAttribute("updated_record", authService.find(authorId));
//    }
     
     
         @Override
    public void init() throws ServletException {
        // Ask Spring for object to inject
        ServletContext sctx = getServletContext();
        WebApplicationContext ctx
                = WebApplicationContextUtils.getWebApplicationContext(sctx);
        authService = (AuthorService) ctx.getBean("authorService");

    }
    
    
           private void setUpdateAttributes(HttpServletRequest request) throws ClassNotFoundException, SQLException {

//        Integer authorId = Integer.parseInt(request.getParameter(UPDATE_AUTHOR_ID_PARAM));
        String authorId = request.getParameter(UPDATE_AUTHOR_ID_PARAM);
        Author author = authService.findById(authorId);
        request.setAttribute(AUTHOR_ID_PARAM, authorId);
        request.setAttribute(UPDATED_RECORD_PARAM, author);
    }

    private void setInsertAttributes(HttpServletRequest request) throws Exception {

        String insertValue = request.getParameter("insert_value");
        Author author = new Author();
        author.setAuthorName(insertValue);
        authService.addNewAuthor(author);
        
//        request.setAttribute("authorList", authService.getAuthorList());
        request.setAttribute("authorList", authService.findAll());
        
        
    }
    
    


    private void setDeleteAttributes(HttpServletRequest request) throws Exception {
        
//        int authorId = Integer.parseInt(request.getParameter("delete_author_id"));
        String authorId = request.getParameter("delete_author_id");
        
          Author author = authService.findById(authorId);
          
        
        
//        authService.deleteAuthorById(authorId);
        authService.deleteAuthorById(author);
        
        
        
//        request.setAttribute("authorList", authService.getAuthorList());
        request.setAttribute("authorList", authService.findAll());

    }

//    private void setConfirmUpdateAttributesOld (HttpServletRequest request) throws SQLException, Exception {
//
//        String newName = request.getParameter("new_name");
//        Integer id = Integer.parseInt(request.getParameter("updated_author_id"));
////        String id = request.getParameter("updated_author_id");
//        
////        authService.modifyAuthorById(newName, id);
//          authService.updateAuthor(id,newName);
//
//        request.setAttribute(AUTHOR_LIST, authService.findAll());
//
//    }
    
        private void setConfirmUpdateAttributes(HttpServletRequest request) throws SQLException, Exception {

//        Integer id = Integer.parseInt(request.getParameter(UPDATED_AUTHOR_ID));
//        Integer id = Integer.parseInt(request.getParameter(UPDATED_AUTHOR_ID));
String id = request.getParameter(UPDATED_AUTHOR_ID);
        String newName= request.getParameter("new_name");
//        String dateAdded = request.getParameter("new_date_added");

//        Double newPrice;
//        try {
//            newPrice = EmptyStringToNullDouble(request.getParameter("new_price"));
//        } catch (IllegalArgumentException iae) {
//            newPrice = null;
//            request.setAttribute("notNumericPrice","yes");
//        }

//        String newUrl = request.getParameter("new_url");
        
        Author author = authService.findById(id);
        author.setAuthorName(newName);
        
        
        authService.updateAuthor(author);
        
//        authService.modifyProduceById(id, authorName, dateAdded);

        request.setAttribute(AUTHOR_LIST, authService.findAll());

    }

    private void setInitialAttributes(HttpSession session, HttpServletRequest request, ServletContext ctx) throws ClassNotFoundException, SQLException {
        session.setAttribute("mode", "READ");
        session.setAttribute("color", "black");
        
//        request.setAttribute("authorList", authService.getAuthorList());
        request.setAttribute("authorList", authService.findAll());
        
        
        ctx.setAttribute("show_hide_user_button", "SHOW USER");
    }

//    private void showUser(ServletContext ctx,HttpServletRequest request){
//        ctx.setAttribute("user","author: Mariusz Cendrowski");
//    }
    private void switchModeAttributes(HttpSession session, HttpServletRequest request) throws Exception {

        if ((session.getAttribute("mode").toString()).equalsIgnoreCase("READ")) {
            session.setAttribute("mode", "EDIT");
            session.setAttribute("color", "red");
        } else {
            session.setAttribute("mode", "READ");
            session.setAttribute("color", "black");
        }

//        request.setAttribute("authorList", authService.getAuthorList());
        request.setAttribute("authorList", authService.findAll());

    }

    private void showHideUserAttributes(ServletContext ctx, HttpServletRequest request) throws Exception {

        if ((ctx.getAttribute("show_hide_user_button").toString()).equalsIgnoreCase("SHOW USER")) {
            ctx.setAttribute("show_hide_user_button", "HIDE USER");
            ctx.setAttribute("user", "user: Mariusz Cendrowski");
        } else {
            ctx.setAttribute("show_hide_user_button", "SHOW USER");
            ctx.setAttribute("user", "");
        }

//        request.setAttribute("authorList", authService.getAuthorList());
        request.setAttribute("authorList", authService.findAll());

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        ServletContext ctx = request.getServletContext();

        String fontColor = request.getParameter("fontColor");

//        configDbConnection();

        String destinationPage = RESULT_PAGE;

//        configDbConnection();
        try {
            /* TODO output your page here. You may use following sample code. */
//         MockAuthorDao ns = new MockAuthorDao();
//         AuthorService ns = new AuthorService();

//configDbConnection();

            
              if (request.getParameter("back_to_start") != null) {
//                setInitialAttributes(session, request, ctx);
                destinationPage = START_PAGE;
            }

              if (request.getParameter("initial_settings") != null) {
                setInitialAttributes(session, request, ctx);
                destinationPage = RESULT_PAGE;
            }

            if (request.getParameter("reset_mode") != null) {
                setInitialAttributes(session, request, ctx);
                destinationPage = RESULT_PAGE;
            }

            if (request.getParameter("switch_mode") != null) {
                switchModeAttributes(session, request);
                destinationPage = RESULT_PAGE;
            }

            if (request.getParameter("show_hide_user") != null) {
                showHideUserAttributes(ctx, request);
                destinationPage = RESULT_PAGE;
            }

            if (request.getParameter("update") != null) {
                setUpdateAttributes(request);
                destinationPage = UPDATE_PAGE;
            } else if (request.getParameter("insert") != null) {
                setInsertAttributes(request);
                destinationPage = RESULT_PAGE;
            } else if (request.getParameter("delete") != null) {
                setDeleteAttributes(request);
                destinationPage = RESULT_PAGE;
            } else if (request.getParameter("confirm_update") != null) {
                setConfirmUpdateAttributes(request);
                destinationPage = RESULT_PAGE;
            }

        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage());
        }
//        RequestDispatcher view
//                = request.getRequestDispatcher(destinationPage);
//        view.forward(request, response);

        RequestDispatcher dispatcher
                = getServletContext()
                .getRequestDispatcher(response.encodeURL(destinationPage));
        dispatcher.forward(request, response);

    }

//    private void configDbConnection() throws NamingException, Exception {
//        if(dbJndiName == null) {
//            authService.getDao().initDao(driverClass, url, userName, password);   
//        } else {
//            /*
//             Lookup the JNDI name of the Glassfish connection pool
//             and then use it to create a DataSource object.
//             */
//            Context ctx = new InitialContext();
//            DataSource ds = (DataSource) ctx.lookup(dbJndiName);
//            authService.getDao().initDao(ds);
//        }
//    }

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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
