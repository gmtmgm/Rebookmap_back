package BookMap.PentaRim.Repository;

import BookMap.PentaRim.BookMap.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookListRepository extends JpaRepository<BookListEntity,Long> {
    List<BookListEntity> findByBookMapDetailEntityOrderByIndex(BookMapDetailEntity bookMapDetailEntity);
    BookListEntity findByBookListId(Long bookListId);
}