package BookMap.PentaRim.service;

import BookMap.PentaRim.memo.Memo;
import org.springframework.stereotype.Service;

@Service
public class MemoServiceImpl implements MemoService{

    @Override
    public Memo createMemo(String string) { //일단 문자열받아서 만드는거만 구현했음 아이디 받는거랑 메모 아디 받는거 구현요망
        Memo memo = new Memo();
        memo.setData(string);

        return memo;
    }


}
