/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author mcendrowski
 */
@Alternative
//@SessionScoped
@Dependent
public class MockAuthorDao implements AuthorDaoStrategy, Serializable {

    private List<Author> listOfAuthors;
    
    
    
       
    public MockAuthorDao() {
//        Author author=null;
//        listOfAuthors= new ArrayList<>();
//        
//        author = new Author();
//        author.setAuthorName("Agatha Christie");
//        author.setDateAdded(LocalDate.now().minusDays(10));        
//        listOfAuthors.add(author);
//        
//        author = new Author();
//        author.setAuthorName("Michael Crichton");
//        author.setDateAdded(LocalDate.now().minusDays(8));        
//        listOfAuthors.add(author);
//        
//        author = new Author();
//        author.setAuthorName("Peter Drucker");
//        author.setDateAdded(LocalDate.now().minusDays(6));        
//        listOfAuthors.add(author);
        initFakeDbResultSet();
    }

    public int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException {
        return 1;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Author> getAuthorList() {
        return listOfAuthors;
    }

    public void setListOfAuthors(List<Author> listOfAuthors) {
        this.listOfAuthors = listOfAuthors;
    }

//    public static void main(String[] args) {
//        MockAuthorDao as = new MockAuthorDao();
//        System.out.println(as.getListOfAuthors().get(2).getAuthorName());
//    }
    private void initFakeDbResultSet() {
        Author author = null;
        listOfAuthors = new ArrayList<>();

        author = new Author();
        author.setAuthorId(1);
        author.setAuthorName("Agatha Christie");
        author.setDateAdded(new Date());
        listOfAuthors.add(author);

        author = new Author();
        author.setAuthorId(2);
        author.setAuthorName("Michael Crichton");
        author.setDateAdded(new Date());
        listOfAuthors.add(author);

        author = new Author();
        author.setAuthorId(3);
        author.setAuthorName("Peter Drucker");
        author.setDateAdded(new Date());
        listOfAuthors.add(author);

//        authors = Arrays.asList(
//                new Author(1,"Agatha Christie",LocalDate.now().minusDays(10)),
//                new Author(2,"Agatha Christie",LocalDate.now().minusDays(10)),
//                new Author(3,"Agatha Christie",LocalDate.now().minusDays(10))
//        )
    }

    @Override
    public int deleteAuthorRecord(String primaryKey, int value) throws SQLException, ClassNotFoundException {
        return 1;
    }

    public int updateAuthorById(List<String> colNames, List<Object> colValues, String primaryKey, Object primaryKeyValue) throws ClassNotFoundException, SQLException, Exception {
        return 1;
    }

    public int insertAuthorRecord(List<String> colNames, List<Object> colValues) throws SQLException, Exception {

        return 1;
    }

    @Override
    public DBStrategy getDb() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDb(DBStrategy db) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Author getAuthorById(Object idValue) throws ClassNotFoundException, SQLException {
        
       Author author = new Author();
       return author;
    }
//    public Map<String, Object> findRecordById(String tableName, Object idValue) throws SQLException{
//        
//        Map<String, Object> map = new HashMap<>();
//        
//        map.put("anything", 1);
//        
//        return map;
//        
//    }

    @Override
    public void initDao(String driver, String url, String user, String pwd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDriver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDriver(String driver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUrl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUrl(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUser(String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPwd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPwd(String pwd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateAuthorById(String authorName, Integer authorId) throws ClassNotFoundException, SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertAuthorRecord(String authorName) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteAuthorById(Integer id) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
