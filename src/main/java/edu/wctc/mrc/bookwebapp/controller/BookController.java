/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.controller;

//import edu.wctc.mrc.bookwebapp.ejb.AuthorFacade;
//import edu.wctc.mrc.bookwebapp.ejb.BookFacade;
import edu.wctc.mrc.bookwebapp.entity.Author;
import edu.wctc.mrc.bookwebapp.entity.Book;
import edu.wctc.mrc.bookwebapp.service.AuthorService;
import edu.wctc.mrc.bookwebapp.service.BookService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author MCENDROWSKI
 */
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {
    
    
//    @Inject
//    private BookFacade bookService;
//    @Inject
//    private AuthorFacade authService;
    
    // DO THIS INSTEAD (see init() method):
    private BookService bookService;
    private AuthorService authService;

    private static final String RESULT_PAGE = "/viewAllBooks.jsp";
    private static final String ACTION_PARAM = "action";
    private static final String EXECUTE_UPDATE = "update";
    private static final String EXECUTE_INSERT = "insert";
    private static final String EXECUTE_DELETE = "delete";
    private static final String EXECUTE_SWITCH_MODE = "switch mode";
    private static final String EXECUTE_SET_READ_MODE = "set read mode";
    private static final String UPDATE_PAGE = "/updateBookDetails.jsp";
    private static final String INSERT_PAGE = "/insertBookDetails.jsp";
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
    
        @Override
    public void init() throws ServletException {
        // Ask Spring for object to inject
        ServletContext sctx = getServletContext();
        WebApplicationContext ctx
                = WebApplicationContextUtils.getWebApplicationContext(sctx);
        authService = (AuthorService) ctx.getBean("authorService");
        bookService = (BookService) ctx.getBean("bookService");
    }
    
    
    private void setUpdateAttributes(HttpServletRequest request) throws ClassNotFoundException, SQLException {

//        Integer bookId = Integer.parseInt(request.getParameter("update_book_id"));
        String bookId = request.getParameter("update_book_id");
//        String authorId = request.getParameter("update_author_id");
        
        request.setAttribute("book_id", bookId);
        
//        request.setAttribute("updated_record", authService.getAuthorById(authorId));        
        request.setAttribute("updated_record", bookService.findById(bookId));       
        
        request.setAttribute("authorList", authService.findAll());
    }
    
        private void setInsertAttributes(HttpServletRequest request) throws ClassNotFoundException, SQLException {

//        Integer bookId = Integer.parseInt(request.getParameter("update_book_id"));
//        String authorId = request.getParameter("update_author_id");
        
//        request.setAttribute("book_id", bookId);
        
//        request.setAttribute("updated_record", authService.getAuthorById(authorId));        
//        request.setAttribute("updated_record", bookService.find(bookId));       
        
        request.setAttribute("authorList", authService.findAll());
    }
        

    private void setConfirmInsertAttributes(HttpServletRequest request) throws Exception {

//        String insertValue = request.getParameter("insert_value");
//        bookService.addNewBook(insertValue);
//        
////        request.setAttribute("authorList", authService.getAuthorList());
//        request.setAttribute("bookList", bookService.findAll());
        
        
        
//               Integer id = Integer.parseInt(request.getParameter("updated_book_id"));
        String newTitle = request.getParameter("new_insert_title");
        String newIsbn = request.getParameter("new_insert_isbn");
        Integer newAuthorId = Integer.parseInt(request.getParameter("new_insert_author_id"));
        Author newAuthor = new Author(newAuthorId);
//        String id = request.getParameter("updated_author_id");
        
//        authService.modifyAuthorById(newName, id);

        Book newBook = new Book();
//        newBook.setBookId(id);
        newBook.setTitle(newTitle);
        newBook.setIsbn(newIsbn);
        newBook.setAuthorId(newAuthor);

//          bookService.addNewBook(newBook);
            bookService.updateBook(newBook);

        request.setAttribute("bookList", bookService.findAll());
          
        
        
    }

    private void setDeleteAttributes(HttpServletRequest request) throws Exception {
        
//        int authorId = Integer.parseInt(request.getParameter("delete_author_id"));
        String bookId = request.getParameter("delete_book_id");
        
//        authService.deleteAuthorById(authorId);

        Book newBook = bookService.findById(bookId);
        
        bookService.deleteBookById(newBook);
        
        
//        request.setAttribute("authorList", authService.getAuthorList());
        request.setAttribute("bookList", bookService.findAll());

    }

//     TEMPORARILY HIDDEN!!!!!!!!!!!!!!!!!!
    private void setConfirmUpdateAttributes(HttpServletRequest request) throws SQLException, Exception {

        
        Integer id = Integer.parseInt(request.getParameter("updated_book_id"));
        String newTitle = request.getParameter("new_title");
        String newIsbn = request.getParameter("new_isbn");
        Integer newAuthorId = Integer.parseInt(request.getParameter("new_author_id"));
        Author newAuthor = new Author(newAuthorId);
        
        Book newBook = new Book();
        newBook.setBookId(id);
        newBook.setTitle(newTitle);
        newBook.setIsbn(newIsbn);
        newBook.setAuthorId(newAuthor);
        
        
        
//        String id = request.getParameter("updated_author_id");
        
//        authService.modifyAuthorById(newName, id);
//          bookService.updateBook(id,newTitle,newIsbn,newAuthor);
          
          bookService.updateBook(newBook);

        request.setAttribute("bookList", bookService.findAll());
         

    }

    private void setInitialAttributes(HttpSession session, HttpServletRequest request, ServletContext ctx) throws ClassNotFoundException, SQLException {
        session.setAttribute("mode", "READ");
        session.setAttribute("color", "black");
        
//        request.setAttribute("authorList", authService.getAuthorList());
        request.setAttribute("bookList", bookService.findAll());
        
        
        ctx.setAttribute("show_hide_user_button", "SHOW USER");
    }
    
     private void backToStartAttributes(HttpSession session, HttpServletRequest request, ServletContext ctx) throws ClassNotFoundException, SQLException {
        session.setAttribute("mode", "READ");
        session.setAttribute("color", "black");
        
//        request.setAttribute("authorList", authService.getAuthorList());
        request.setAttribute("bookList", bookService.findAll());
        
        
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
        request.setAttribute("bookList", bookService.findAll());

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
        request.setAttribute("bookList", bookService.findAll());

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
            }

            if (request.getParameter("insert") != null) {
                setInsertAttributes(request);
                destinationPage = INSERT_PAGE;

            } else if (request.getParameter("confirm_insert") != null) {
                setConfirmInsertAttributes(request);
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
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
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
    
//    public static void testBookAll() {
//        bookService.findAll();
//    }

}
