/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mcendrowski
 */
public class MockAuthorDao implements AuthorDaoStrategy {
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
    private void initFakeDbResultSet(){
         Author author=null;
        listOfAuthors= new ArrayList<>();
        
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
    public void deleteAuthorRecord(String primaryKey, int value) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
