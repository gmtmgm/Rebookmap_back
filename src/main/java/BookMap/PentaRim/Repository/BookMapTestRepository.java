package BookMap.PentaRim.Repository;

import BookMap.PentaRim.BookMap.BookMapTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMapTestRepository extends JpaRepository<BookMapTest, Long> {
}
