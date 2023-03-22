package BookMap.PentaRim.Repository;

import BookMap.PentaRim.Book.Book;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BookRepositoryImpl implements BookRepository{

    private static Map<Long, Book> Bookstore = new ConcurrentHashMap<>();

    @Override
    public void save(Book book) {
        Bookstore.put(book.getIsbn(), book);
    }

    @Override
    public Book findByIsbn(Long isbn) {
        return Bookstore.get(isbn);
    }
}
