package BookMap.PentaRim.Repository;

import BookMap.PentaRim.BookMap.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookListRepository extends JpaRepository<BookListEntity,Long> {
    List<BookListEntity> findByBookMapDetailOrderByIndex(BookMapDetailEntity bookMapDetailEntity);
    BookListEntity findByBookListId(Long bookListId);
    List<BookListEntity> findByBookMapDetail(BookMapDetailEntity bookMapDetailEntity);
}
