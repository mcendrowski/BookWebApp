/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author MCENDROWSKI
 */
public class AuthorService {
    private AuthorDaoStrategy dao = new AuthorDao();
//    private AuthorDaoStrategy dao = new MockAuthorDao();
    
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
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
//        AuthorService srv = new AuthorService();
//        List<Author> authors = srv.getAuthorList();
//        System.out.println(authors);
        testModifyAuthorById();
    }
    
    public static void testModifyAuthorById() throws ClassNotFoundException, SQLException, Exception{
    AuthorService srv = new AuthorService();
    List<String> colNames = Arrays.asList("author_name", "date_added");
    List<Object> colValues = Arrays.asList("Lucifer", "2001-02-11");
    srv.modifyAuthorById(colNames, colValues, "author_id", 1);
}
    
  
}
