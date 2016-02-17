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
public interface AuthorDaoStrategy {

    //    db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
   public abstract List<Author> getAuthorList() throws ClassNotFoundException, SQLException;
    public abstract void deleteAuthorRecord(String primaryKey,int value) throws SQLException, ClassNotFoundException;
}