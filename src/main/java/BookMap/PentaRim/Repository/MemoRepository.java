package BookMap.PentaRim.Repository;

import BookMap.PentaRim.memo.Memo;

public interface MemoRepository {
    void save(Memo memo);

    Memo findByMemoId(Long id);
}