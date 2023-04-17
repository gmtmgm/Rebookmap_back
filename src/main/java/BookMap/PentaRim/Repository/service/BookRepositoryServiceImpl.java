package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookRepositoryServiceImpl {

    private final BookRepository bookRepository;

    public BookRepositoryServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public void join(Book book) {
        bookRepository.save(book);
    }
    public Book findBookMap(Long isbn) {
        return bookRepository.findByIsbn(isbn);
    }
}
