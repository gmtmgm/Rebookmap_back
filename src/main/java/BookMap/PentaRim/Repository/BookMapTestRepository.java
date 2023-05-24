package BookMap.PentaRim.Repository;

import BookMap.PentaRim.BookMap.BookMapTest;
import BookMap.PentaRim.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapTestRepository extends JpaRepository<BookMapTest, Long> {

    List<BookMapTest> findAllByUser(User user);
}
