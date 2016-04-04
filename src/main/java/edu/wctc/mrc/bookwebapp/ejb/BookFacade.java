/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.ejb;

import edu.wctc.mrc.bookwebapp.model.Author;
import edu.wctc.mrc.bookwebapp.model.Book;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author MCENDROWSKI
 */
@Stateless
public class BookFacade extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "edu.wctc.mrc_BookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }
//    public List<Book> findByTitle(String title){
//        String jpql = "select b from Book b where b.title= ?1";
//        TypedQuery<Book> q = getEntityManager().createQuery(jpql,Book.class);
//        q.setParameter(1,title);
//        return q.getResultList();
//    }
    
          public void updateBook(Integer id,String title,String isbn,Author authorId){
        Book book = new Book();
//        author.setAuthorId(Integer.parseInt(id)); 
        book.setBookId(id);
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthorId(authorId);
//        this.create(author);
        this.getEntityManager().merge(book); 
        
    }
    
    public void addNewBook(String title,String isbn,Author authorId){
         Book book = new Book();
         book.setTitle(title);
         book.setIsbn(isbn);
        book.setAuthorId(authorId);
//         this.getEntityManager().merge(author);
         this.create(book);
         
         
         
    }
    
    public void deleteBookById(Object id){
//         Author author = this.find(id);
        Book book = this.find(Integer.parseInt(id.toString()));
        remove(book);
        
    }
    
    public static void testBookAll() {
        BookFacade bf = new BookFacade();
        System.out.println(bf.findAll());
    }
    
    public static void main(String[] args) {
        testBookAll();
    }
    
}
