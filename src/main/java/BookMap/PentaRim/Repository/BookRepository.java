package BookMap.PentaRim.Repository;

import BookMap.PentaRim.Book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //보니까 isbn으로 찾기로했는데 이거 이래도되나 될듯?


        Optional<Book> findByIsbn(String isbn);



    boolean existsByIsbn(String isbn);
}
