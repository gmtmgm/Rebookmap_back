package BookMap.PentaRim.Repository;

import BookMap.PentaRim.Book.Book;



public interface BookRepository {

    //보니까 isbn으로 찾기로했는데 이거 이래도되나 될듯?

    void save(Book book);

    Book findByIsbn(String isbn);
}
