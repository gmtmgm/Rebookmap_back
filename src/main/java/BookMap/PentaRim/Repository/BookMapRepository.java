package BookMap.PentaRim.Repository;

import BookMap.PentaRim.BookMap.*;
import BookMap.PentaRim.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookMapRepository extends JpaRepository<BookMapEntity,Long> {
    boolean existsByBookMapId(Long bookMapId);
    List<BookMapEntity> findByUser(User user);
    BookMapEntity findByBookMapId(Long bookMapId);
}
