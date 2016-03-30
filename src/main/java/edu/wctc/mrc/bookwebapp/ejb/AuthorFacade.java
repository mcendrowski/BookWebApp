/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.ejb;

import edu.wctc.mrc.bookwebapp.model.Author;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mcendrowski
 */
@Stateless
public class AuthorFacade extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "edu.wctc.mrc_BookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }
    
      public void updateAuthor(String id,String name){
        Author author = new Author();
        author.setAuthorId(Integer.parseInt(id));
        author.setAuthorName(name);
        this.create(author);
//        this.getEntityManager().merge(author); 
        
    }
    
    public void addNewAuthor(String name){
         Author author = new Author();
         author.setAuthorName(name);
         author.setDateAdded(new Date());
         this.getEntityManager().merge(author);
         
         
    }
    
    public void deleteAuthorById(Object id){
        Author author = this.find(id);
        remove(author);
        
    }

    
}
