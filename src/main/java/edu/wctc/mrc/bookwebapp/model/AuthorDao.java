/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mcendrowski
 */
public class AuthorDao implements AuthorDaoStrategy {
//    customer terminology
    // opening and closing the connection
    // driver class name, pasword come from outside
    // should come from config file
    // homework - deleting using id
    // pass table, primary key and value

    private DBStrategy db = new MySqlDBStrategy();
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/book";
    private final String USER = "root";
    private final String PWD = "admin";

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
    public void deleteAuthorRecord(String primaryKey,int value) throws SQLException, ClassNotFoundException{
         db.openConnection(DRIVER, URL, USER, PWD);
         db.deleteRecord("author", primaryKey, value);
         db.closeConnection();
    }

public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        testGetAuthorList();
        testDeleteAuthorRecord();
    }

public static void testGetAuthorList() throws ClassNotFoundException, SQLException {
        AuthorDaoStrategy dao = new AuthorDao();
        List<Author> authors = dao.getAuthorList();
        System.out.println(authors);
    }

public static void testDeleteAuthorRecord() throws ClassNotFoundException, SQLException {
        AuthorDaoStrategy dao = new AuthorDao();
        dao.deleteAuthorRecord("author_id", 4);
       
    }

}