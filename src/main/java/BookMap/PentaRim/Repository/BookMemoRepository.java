package BookMap.PentaRim.Repository;

import BookMap.PentaRim.Book.BookMemo;
import BookMap.PentaRim.Book.BookPersonal;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface BookMemoRepository extends JpaRepository<BookMemo, Long>{
    List<BookMemo> findByBookPersonalOrderBySavedDesc(BookPersonal bookPersonal);
    void deleteAllByBookPersonal(BookPersonal bookPersonal);
}
