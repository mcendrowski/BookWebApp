/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MCENDROWSKI
 */
public interface AuthorDaoStrategy {

    //    db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
   public abstract List<Author> getAuthorList() throws ClassNotFoundException, SQLException;
    public abstract int deleteAuthorRecord(String primaryKey,int value) throws SQLException, ClassNotFoundException;
     public abstract int deleteAuthorById(Integer id) throws ClassNotFoundException, SQLException;
    public abstract int updateAuthorById(String authorName, Integer authorId) throws ClassNotFoundException, SQLException, Exception;
      public abstract int insertAuthorRecord(String authorName) throws SQLException, Exception;
       public abstract DBStrategy getDb();  
    

    public abstract void setDb(DBStrategy db);
    public abstract Author getAuthorById(Integer idValue) throws ClassNotFoundException, SQLException;
    
    public abstract void initDao(String driver, String url, String user, String pwd);
    
    public abstract String getDriver();

    public abstract void setDriver(String driver);

    public abstract String getUrl(); 

    public abstract void setUrl(String url); 

    public abstract String getUser();

    public abstract void setUser(String user);

    public abstract String getPwd();

    public abstract void setPwd(String pwd);
    
    public abstract Map<String, Object> getAuthorId(Integer idValue) throws ClassNotFoundException, SQLException;
    
}
