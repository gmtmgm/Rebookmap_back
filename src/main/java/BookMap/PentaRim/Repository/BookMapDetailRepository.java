package BookMap.PentaRim.Repository;

import BookMap.PentaRim.BookMap.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapDetailRepository extends JpaRepository<BookMapDetailEntity,Long> {
    List<BookMapDetailEntity> findByBookMapEntityOrderByIndex(BookMapEntity bookMapEntity);
    BookMapDetailEntity findByBookMapDetailId(Long bookMapDetailId);
    List<BookMapDetailEntity> findByBookMapEntity(BookMapEntity bookMapEntity);
    boolean existsByBookMapDetailId(Long bookMapDetailId);
}
