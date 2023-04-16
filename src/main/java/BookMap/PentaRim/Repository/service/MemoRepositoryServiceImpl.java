package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.Repository.MemoRepository;
import BookMap.PentaRim.Repository.MemoRepositoryImpl;
import BookMap.PentaRim.memo.Memo;

public class MemoRepositoryServiceImpl {

    private final MemoRepository memoRepository = new
            MemoRepositoryImpl();
    public void join(Memo memo) {
        memoRepository.save(memo);
    }
    public Memo findMemo(Long memoId) {
        return memoRepository.findByMemoId(memoId);
    }
}
