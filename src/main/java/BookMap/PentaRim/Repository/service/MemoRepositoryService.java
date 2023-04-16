package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.memo.Memo;

public interface MemoRepositoryService {

    void join(Memo memo);

    Memo findMemo(Long MemoId);
}
