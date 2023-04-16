package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.BookMap;

public interface BookRepositoryService {

    void join(Book book);

    Book findBook(Long BookId);
}
