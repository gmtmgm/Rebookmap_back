package BookMap.PentaRim.Repository;

import BookMap.PentaRim.Book.Book;
import org.springframework.stereotype.Component;

@Component
public interface BookRepository {

    void save(Book book);

    Book findByIsbn(Long isbn);
}
