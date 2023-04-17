package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.Book.Book;

public interface BookRepositoryService {

    void join(Book book);

    Book findBook(Long BookId);
}
