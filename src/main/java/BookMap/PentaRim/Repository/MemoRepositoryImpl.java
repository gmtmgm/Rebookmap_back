package BookMap.PentaRim.Repository;

import BookMap.PentaRim.memo.Memo;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoRepositoryImpl implements MemoRepository{

    private static Map<Long, Memo> store = new ConcurrentHashMap<>();
    @Override
    public void save(Memo memo) {
        store.put(memo.getMemoId(), memo);
    }

    @Override
    public Memo findByMemoId(Long id) {
        return store.get(id);
    }
}