package BookMap.PentaRim.Repository;

import BookMap.PentaRim.BookMap.BookMapScrapEntity;
import BookMap.PentaRim.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookMapScrapRepository extends JpaRepository<BookMapScrapEntity,Long> {
    List<BookMapScrapEntity> findByUserOrderByBookMapSaveTime(User user);
}
