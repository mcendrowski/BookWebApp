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
//    @Inject
//    private AuthorDaoStrategy dao; // = new AuthorDao();
    private AuthorDaoStrategy dao = new AuthorDao();

    public AuthorService() {
    }

    public AuthorDaoStrategy getDao() {
        return dao;
    }

    public void setDao(AuthorDaoStrategy dao) {
        this.dao = dao;
    }
    
    
    public int deleteAuthorbyId(Object id) throws ClassNotFoundException, SQLException{
        return dao.deleteAuthorById(id);
    }
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException
    {
        return dao.getAuthorList();
    }
    public int modifyAuthorById(List<String> colNames, List<Object> colValues, String primaryKey, Object primaryKeyValue) throws ClassNotFoundException, SQLException, Exception{
      return dao.updateAuthorById(colNames, colValues, primaryKey, primaryKeyValue);
    } 
    
    public int addNewAuthor(List<String> colNames, List<Object> colValues) throws SQLException, Exception {
      return dao.insertAuthorRecord(colNames, colValues);
    } 
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
//        AuthorService srv = new AuthorService();
//        List<Author> authors = srv.getAuthorList();
//        System.out.println(authors);
//        testModifyAuthorById();
        testAddNewAuthor();
    }
    
    public static int testModifyAuthorById() throws ClassNotFoundException, SQLException, Exception{
    int result;
    AuthorService srv = new AuthorService();
    List<String> colNames = Arrays.asList("author_name", "date_added");
    List<Object> colValues = Arrays.asList("Lucifer", "2001-02-11");
    result = srv.modifyAuthorById(colNames, colValues, "author_id", 1);
    return result;
}
    
       public static int testAddNewAuthor() throws ClassNotFoundException, SQLException, Exception{
    int result;
    AuthorService srv = new AuthorService();
    List<String> colNames = Arrays.asList("author_name");
    List<Object> colValues = Arrays.asList("Leonardo");
    result = srv.addNewAuthor(colNames, colValues);
    return result;
}
    
  
}
