package BookMap.PentaRim.Repository.service;
import BookMap.PentaRim.Repository.MemoRepository;
import BookMap.PentaRim.memo.Memo;

public class MemoRepositoryServiceImpl {

    private final MemoRepository memoRepository;

    public MemoRepositoryServiceImpl(MemoRepository memoRepository){
        this.memoRepository = memoRepository;
    }

    public void join(Memo memo) {
        memoRepository.save(memo);
    }
    public Memo findMemo(Long memoId) {
        return memoRepository.findByMemoId(memoId);
    }
}
