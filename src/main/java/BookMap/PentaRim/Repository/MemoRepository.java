package BookMap.PentaRim.Repository;

import BookMap.PentaRim.memo.Memo;
import org.springframework.stereotype.Component;

@Component
public interface MemoRepository {

    void save(Memo memo);

    Memo findByMemoId(Long id);
}
