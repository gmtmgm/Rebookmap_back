package BookMap.PentaRim.Repository;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Book.BookState;
import BookMap.PentaRim.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookPersonalRepository extends JpaRepository<BookPersonal, Long> {
    List<BookPersonal> findByUser(User user);
    Optional<BookPersonal> findByUserAndBook(User user, Book book);
    boolean existsByBookAndUser(Book book, User user);

    //@Query("SELECT bp FROM BookPersonal bp WHERE bp.user.id = :userId AND bp.startDate <= :endDate AND bp.endDate >= :startDate")
    //List<BookPersonal> findAllBetweenDatesForUser(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("userId") Long userId);

    List<BookPersonal> findAllByUserAndEndDateBetween(User user,LocalDate monthStart, LocalDate monthEnd);
    Integer countBookPersonalByUserAndBookStateAndEndDateBetween(User user,BookState bookState, LocalDate monthStart, LocalDate monthEnd);

    @Query("SELECT bp.book FROM BookPersonal bp GROUP BY bp.book ORDER BY count (bp.book.id) DESC LIMIT 2")
    List<Book> findBooksTop2ByOrderByBookCountDesc();

    @Query("SELECT bp.book FROM BookPersonal bp GROUP BY bp.book ORDER BY count (bp.book.id) DESC LIMIT 5")
    List<Book> findBooksTop5ByOrderByBookCountDesc();

    @Query("SELECT bp.book FROM BookPersonal bp GROUP BY bp.book ORDER BY count (bp.book.id) DESC LIMIT 10")
    List<Book> findBooksTop10ByOrderByBookCountDesc();


    List<BookPersonal> findTop4ByUserOrderBySavedDesc(User user);

    List<BookPersonal> findBookPersonalsByUserOrderBySavedDesc(User user);

    List<BookPersonal> findBookPersonalsByUserAndBookStateOrderBySaved(User user, BookState bookState);

    boolean existsByUserAndBook_Isbn(User user, String isbn);


}
