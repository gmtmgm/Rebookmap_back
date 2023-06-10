package BookMap.PentaRim.Repository;

import BookMap.PentaRim.BookMap.*;
import BookMap.PentaRim.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookMapRepository extends JpaRepository<BookMapEntity,Long> {

    List<BookMapEntity> findByUserOrderByBookMapSaveTime(User user);
    List<BookMapEntity> findTop3ByUserOrderByBookMapSaveTime(User user);
    List<BookMapEntity> findAllByMapHashTags(Long bookMapId);
    List<BookMapEntity> findAllByBookMapTitleContaining(String bookMapTitle);
}
