package BookMap.PentaRim.Repository;

import BookMap.PentaRim.Book.BookMemo;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BookMemoRepository extends JpaRepository<BookMemo, Long>{

}
