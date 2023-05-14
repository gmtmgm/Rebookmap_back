package BookMap.PentaRim.Repository;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookPersonalRepository extends JpaRepository<BookPersonal, Long> {
    List<BookPersonal> findByUser(User user);
    Optional<BookPersonal> findByUserAndBook(User user, Book book);
}
