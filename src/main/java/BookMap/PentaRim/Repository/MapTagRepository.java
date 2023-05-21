package BookMap.PentaRim.Repository;

import BookMap.PentaRim.BookMap.BookMapTest;
import BookMap.PentaRim.BookMap.MapHashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapTagRepository extends JpaRepository<MapHashTag, Long> {
    List<MapHashTag> findAllByBookMapTest(BookMapTest bookMapTest);
    void deleteAllByBookMapTest(BookMapTest bookMapTest);

    boolean existsByHashTag_Tag(String tag);
    boolean existsByHashTag_Id(Long id);

    List<MapHashTag> findAllByHashTag_Tag(String tag);

}
