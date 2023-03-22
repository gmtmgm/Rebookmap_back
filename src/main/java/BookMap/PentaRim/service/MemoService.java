package BookMap.PentaRim.service;

import BookMap.PentaRim.memo.Memo;

public interface MemoService {

    Memo createMemo(String string);
    void deleteMemo(Memo memo);
    Memo modifyMemo(Memo memo);
}
