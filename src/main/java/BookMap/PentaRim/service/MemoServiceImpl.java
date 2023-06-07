package BookMap.PentaRim.service;

import BookMap.PentaRim.memo.Memo;
import org.springframework.stereotype.Service;

@Service
public class MemoServiceImpl implements MemoService{

    @Override
    public Memo createMemo(String string) {
        Memo memo = new Memo();
        memo.setData(string);

        return memo;
    }


}
