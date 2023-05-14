package BookMap.PentaRim.Repository;

import BookMap.PentaRim.Book.BookMemo;
import BookMap.PentaRim.Book.BookPersonal;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookMemoRepository extends JpaRepository<BookMemo, Long>{
    List<BookMemo> findByBookPersonalOrderBySavedDesc(BookPersonal bookPersonal);

}
