package BookMap.PentaRim.Repository;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookPersonalRepository extends JpaRepository<BookPersonal, Long> {
    List<BookPersonal> findByUser(User user);
    Optional<BookPersonal> findByUserAndBook(User user, Book book);

    @Query("SELECT bp FROM BookPersonal bp WHERE bp.user.id = :userId AND bp.startDate <= :endDate AND bp.endDate >= :startDate")
    List<BookPersonal> findAllBetweenDatesForUser(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("userId") Long userId);
}
