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
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author mcendrowski
 */
//@SessionScoped
@Dependent
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

//    private final String DRIVER = "com.mysql.jdbc.Driver";
//    private final String URL = "jdbc:mysql://localhost:3306/book";
//    private final String USER = "root";
//    private final String PWD = "admin";
    private String driver;
    private String url;
    private String user;
    private String pwd;

    public void initDao(String driver, String url, String user, String pwd) {
        setDriver(driver);
        setUrl(url);
        setUser(user);
        setPwd(pwd);

    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public AuthorDao() {
    }

    public DBStrategy getDb() {
        return db;
    }

    public void setDb(DBStrategy db) {
        this.db = db;
    }

    public int deleteAuthorById(Integer id) throws ClassNotFoundException, SQLException {
        System.out.println(driver);
        System.out.println(url);
        System.out.println(user);
        System.out.println(pwd);
        db.openConnection(driver, url, user, pwd);

        int result = db.deleteById("author", "author_id", id);

        db.closeConnection();
        return result;

    }

    public int updateAuthorById(String authorName, Integer authorId) throws ClassNotFoundException, SQLException, Exception {
//        db.openConnection(driver, url, user, pwd);
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");

        int recordsUpdated = db.updateRecordById("author", Arrays.asList("author_name"),
                Arrays.asList(authorName),
                "author_id", authorId);

        db.closeConnection();
        return recordsUpdated;
    }

//    db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
    @Override
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        db.openConnection(driver, url, user, pwd);

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

    public Author getAuthorById(Integer idValue) throws ClassNotFoundException, SQLException {
//         db.openConnection(driver, url, user, pwd);
         
         db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
         
         

        Map<String, Object> rawData = db.findRecordById("author", "author_id", idValue);

//        Author author = new Author();
        Author author = new Author();
//        Integer id = idValue;
        author.setAuthorId(idValue);

        String name = rawData.get("author_name") == null ? "" : rawData.get("author_name").toString();
        author.setAuthorName(name);
        Date date = rawData.get("date_added") == null ? null : (Date) rawData.get("date_added");
        author.setDateAdded(date);

        db.closeConnection();

        return author;
    }
    
        public Map<String, Object> getAuthorId(Integer idValue) throws ClassNotFoundException, SQLException {
//         db.openConnection(driver, url, user, pwd);
         db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");

        Map<String, Object> rawData = db.findRecordById("author", "author_id", idValue);
        
        db.closeConnection();
        
        return rawData;

////        Author author = new Author();
//        Author author = new Author();
//        Integer id = (Integer) idValue;
//        author.setAuthorId(id);
//
//        String name = rawData.get("author_name") == null ? "" : rawData.get("author_name").toString();
//        author.setAuthorName(name);
//        Date date = rawData.get("date_added") == null ? null : (Date) rawData.get("date_added");
//        author.setDateAdded(date);
//
//        db.closeConnection();
//
//        return author;
    }

    public int deleteAuthorRecord(String primaryKey, int value) throws SQLException, ClassNotFoundException {
        int result;
        db.openConnection(driver, url, user, pwd);
        result = db.deleteRecord("author", primaryKey, value);
        db.closeConnection();
        return result;
    }

    public int insertAuthorRecord(String authorName) throws SQLException, Exception {
        int result;
        db.openConnection(driver, url, user, pwd);

        result = db.insertRecord("author", Arrays.asList("author_name", "date_added"),
                Arrays.asList(authorName, new Date()));

        db.closeConnection();
        return result;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
//        testGetAuthorList();
//        testDeleteAuthorById();
//        testUpdateAuthorById();
//        testInsertAuthor();
        testGetAuthorById();
//        testGetAuthorId();
    }

    public static void testGetAuthorList() throws ClassNotFoundException, SQLException {
        AuthorDaoStrategy dao = new AuthorDao();
        List<Author> authors = dao.getAuthorList();
        System.out.println(authors);
    }

    public static void testGetAuthorById() throws ClassNotFoundException, SQLException {
        AuthorDaoStrategy dao = new AuthorDao();
//        Integer id = 1;
        Author author = dao.getAuthorById(1);

//    Author author = new Author(2);
        
        System.out.println(author);
    }
    
    public static void testGetAuthorId() throws ClassNotFoundException, SQLException {
        AuthorDaoStrategy dao = new AuthorDao();
//        Object object = new Integer(1);
        Map map = dao.getAuthorId(2);
        System.out.println(map);
    }

    public static void testDeleteAuthorById() throws ClassNotFoundException, SQLException {
        AuthorDaoStrategy dao = new AuthorDao();
        dao.deleteAuthorById(6);
    }

    public static void testUpdateAuthorById() throws ClassNotFoundException, SQLException, Exception {
        AuthorDaoStrategy dao = new AuthorDao();
//    List<String> colNames = Arrays.asList("author_name", "date_added");
//    List<Object> colValues = Arrays.asList("Mefisto", "2002-02-11");
        dao.updateAuthorById("Warewolf", 1);
    }

    public static int testInsertAuthor() throws ClassNotFoundException, SQLException, Exception {
        int result;
        AuthorDaoStrategy dao = new AuthorDao();
//    List<String> colNames = Arrays.asList("author_name", "date_added");
//    List<Object> colValues = Arrays.asList("Germanicus", "2007-02-11");
        result = dao.insertAuthorRecord("Frankenstein");
        return result;
    }

}
