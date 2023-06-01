package BookMap.PentaRim.Book;

import BookMap.PentaRim.Repository.BookPersonalRepository;
import BookMap.PentaRim.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BookPersonalRepositoryTest {

    @Autowired
    BookPersonalRepository bookPersonalRepository;

    @Autowired
    BookRepository bookRepository;



}
