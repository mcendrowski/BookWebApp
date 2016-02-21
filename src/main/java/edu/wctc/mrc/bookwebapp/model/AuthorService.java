/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.model;

import java.sql.SQLException;
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
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorService srv = new AuthorService();
        List<Author> authors = srv.getAuthorList();
        System.out.println(authors);
    }
    
  
}
