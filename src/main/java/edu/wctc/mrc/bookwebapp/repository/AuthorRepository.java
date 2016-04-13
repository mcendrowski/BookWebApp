package edu.wctc.mrc.bookwebapp.repository;

//import edu.wctc.web.demo.bookwebapp.entity.Author;
import edu.wctc.mrc.bookwebapp.entity.Author;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jlombardo
 */
public interface AuthorRepository extends JpaRepository<Author, Integer>, Serializable {
    
    @Query("SELECT a FROM Author a JOIN FETCH a.bookSet WHERE a.authorId = (:id)")
    public Author findByIdAndFetchBooksEagerly(@Param("id") Integer id);
//    
    @Query("SELECT a.authorName FROM Author a")
    public Object[] findAllWithNameOnly();
    
    @Query("SELECT a.authorId FROM Author a")
    public Object[] findAuthorAndCountBooks();
    
    @Query("SELECT a FROM Author a JOIN FETCH a.bookSet WHERE a.authorName = (:author_name)")
     public Author findByIdAndFetchBooksEagerly(@Param("author_name") String author_name);
    
    
}

