package BookMap.PentaRim.Repository;

import BookMap.PentaRim.Book.Book;

public interface BookRepository {

    void save(Book book);

    Book findByIsbn(Long isbn);
}
