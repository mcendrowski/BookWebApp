package edu.wctc.mrc.bookwebapp.repository;

//import edu.wctc.web.demo.bookwebapp.entity.Book;
import edu.wctc.mrc.bookwebapp.entity.Book;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jlombardo
 */
public interface BookRepository extends JpaRepository<Book, Integer>, Serializable {
    
}
