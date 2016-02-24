/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author mcendrowski
 */

@SessionScoped
public class AuthorDao implements AuthorDaoStrategy, Serializable {
//    customer terminology
    // opening and closing the connection
    // driver class name, pasword come from outside
    // should come from config file
    // homework - deleting using id
    // pass table, primary key and value
    
//    @Inject
//    private DBStrategy db; // = new MySqlDBStrategy();
    
    private DBStrategy db = new MySqlDBStrategy();
    
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/book";
    private final String USER = "root";
    private final String PWD = "admin";

    public AuthorDao() {
    }

    public DBStrategy getDb() {
        return db;
    }

    public void setDb(DBStrategy db) {
        this.db = db;
    }
    
    
    
    
    public int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException{
         db.openConnection(DRIVER, URL, USER, PWD);
         
         int result = db.deleteById("author","author_id",id);
         
         db.closeConnection();
         return result;
    }
    
        public int updateAuthorById(List<String> colNames, List<Object> colValues, String primaryKey, Object primaryKeyValue) throws ClassNotFoundException, SQLException, Exception{
         db.openConnection(DRIVER, URL, USER, PWD);
         int result = db.updateRecordById("author", colNames, colValues, primaryKey, primaryKeyValue);          
         
         db.closeConnection();
         return result;
    }

//    db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
    @Override
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        db.openConnection(DRIVER, URL, USER, PWD);

        List<Map<String, Object>> rawData = db.findAllRecords("author", 0);

        List<Author> authors = new ArrayList<>();

        for (Map rec : rawData) {
            Author author = new Author();
            Integer id = new Integer(rec.get("author_id").toString());
            author.setAuthorId(id);

            String name = rec.get("author_name") == null ? "" : rec.get("author_name").toString();
            author.setAuthorName(name);
            Date date = rec.get("date_added") == null ? null : (Date) rec.get("date_added");
            author.setDateAdded(date);
            authors.add(author);
        }
        db.closeConnection();


        return authors;
}
    
        public Author getAuthorById(Object idValue) throws ClassNotFoundException, SQLException {
        db.openConnection(DRIVER, URL, USER, PWD);

        Map<String, Object> rawData = db.findRecordById("author","author_id", idValue);

//        Author author = new Author();

        
            Author author = new Author();
            Integer id = new Integer(idValue.toString());
            author.setAuthorId(id);

            String name = rawData.get("author_name") == null ? "" : rawData.get("author_name").toString();
            author.setAuthorName(name);
            Date date = rawData.get("date_added") == null ? null : (Date) rawData.get("date_added");
            author.setDateAdded(date);
            
        
        db.closeConnection();


        return author;
}
    public int deleteAuthorRecord(String primaryKey,int value) throws SQLException, ClassNotFoundException{
         int result;
        db.openConnection(DRIVER, URL, USER, PWD);
         result = db.deleteRecord("author", primaryKey, value);
         db.closeConnection();
         return result;
    }
    
        public int insertAuthorRecord(List<String> colNames, List<Object> colValues) throws SQLException, Exception {
         int result;
         db.openConnection(DRIVER, URL, USER, PWD);
         result=db.insertRecord("author",colNames, colValues);
         db.closeConnection();
         return result;
    }

public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
//        testGetAuthorList();
//        testDeleteAuthorRecord();
//        testUpdateAuthorById();
//        testInsertAuthor();
        testGetAuthorById();
    }

public static void testGetAuthorList() throws ClassNotFoundException, SQLException {
        AuthorDaoStrategy dao = new AuthorDao();
        List<Author> authors = dao.getAuthorList();
        System.out.println(authors);
    }

public static void testGetAuthorById() throws ClassNotFoundException, SQLException {
        AuthorDaoStrategy dao = new AuthorDao();
        Author author = dao.getAuthorById(3);
        System.out.println(author);
    }

public static void testDeleteAuthorRecord() throws ClassNotFoundException, SQLException {
        AuthorDaoStrategy dao = new AuthorDao();
        dao.deleteAuthorRecord("author_id", 4);       
    }


public static void testUpdateAuthorById() throws ClassNotFoundException, SQLException, Exception{
    AuthorDaoStrategy dao = new AuthorDao();
    List<String> colNames = Arrays.asList("author_name", "date_added");
    List<Object> colValues = Arrays.asList("Mefisto", "2002-02-11");
    dao.updateAuthorById(colNames, colValues, "author_id", 1);
}

public static int testInsertAuthor() throws ClassNotFoundException, SQLException, Exception{
    int result;
    AuthorDaoStrategy dao = new AuthorDao();
    List<String> colNames = Arrays.asList("author_name", "date_added");
    List<Object> colValues = Arrays.asList("Germanicus", "2007-02-11");
    result = dao.insertAuthorRecord(colNames, colValues);    
    return result;
}

}
