/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author MCENDROWSKI
 */
@SessionScoped
public class AuthorService implements Serializable {
    @Inject
    private AuthorDaoStrategy dao; // = new AuthorDao();
//    private AuthorDaoStrategy dao = new AuthorDao();

    public AuthorService() {
    }

    public AuthorDaoStrategy getDao() {
        return dao;
    }

    public void setDao(AuthorDaoStrategy dao) {
        this.dao = dao;
    }
    
    public Author getAuthorById(Integer idValue) throws ClassNotFoundException, SQLException{
        return dao.getAuthorById(idValue);
    }
    public int deleteAuthorById(Integer id) throws ClassNotFoundException, SQLException{
        return dao.deleteAuthorById(id);
    }
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException
    {
        return dao.getAuthorList();
    }
    public int modifyAuthorById(String authorName, Integer authorId) throws ClassNotFoundException, SQLException, Exception{
      return dao.updateAuthorById(authorName,authorId);
    } 
    
    public int addNewAuthor(String authorName) throws SQLException, Exception {
      return dao.insertAuthorRecord(authorName);
    } 
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
//        AuthorService srv = new AuthorService();
//        List<Author> authors = srv.getAuthorList();
//        System.out.println(authors);
        testModifyAuthorById();
//        testAddNewAuthor();
//        testDeleteAuthorById();
//        testGetAuthorById();
    }
    
    public static int testModifyAuthorById() throws ClassNotFoundException, SQLException, Exception{
    int result;
    AuthorService srv = new AuthorService();
//    List<String> colNames = Arrays.asList("author_name", "date_added");
//    List<Object> colValues = Arrays.asList("Lucifer", "2001-02-11");
    result = srv.modifyAuthorById("Spiderman", 1);
    return result;
}
    
       public static int testAddNewAuthor() throws ClassNotFoundException, SQLException, Exception{
    int result;
    AuthorService srv = new AuthorService();
//    List<String> colNames = Arrays.asList("author_name");
//    List<Object> colValues = Arrays.asList("Leonardo");
    result = srv.addNewAuthor("Superman");
    return result;
}
       public static int testDeleteAuthorById() throws ClassNotFoundException, SQLException{
           int result;
           AuthorService srv = new AuthorService();
           result = srv.deleteAuthorById(5);
           return result;
       }
       
        public static void testGetAuthorById() throws ClassNotFoundException, SQLException{
//           int result;
           AuthorService srv = new AuthorService();
            System.out.println(srv.getAuthorById(1));
//           return result;
       }
       
       
    
  
}
