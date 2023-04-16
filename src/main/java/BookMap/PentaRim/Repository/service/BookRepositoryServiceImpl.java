package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Repository.BookRepository;
import BookMap.PentaRim.Repository.BookRepositoryImpl;

public class BookRepositoryServiceImpl {

    private final BookRepository bookRepository = new
            BookRepositoryImpl();
    public void join(Book book) {
        bookRepository.save(book);
    }
    public Book findBookMap(Long isbn) {
        return bookRepository.findByIsbn(isbn);
    }
}
