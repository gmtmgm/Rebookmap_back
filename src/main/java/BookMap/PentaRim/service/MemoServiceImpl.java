package BookMap.PentaRim.service;

import BookMap.PentaRim.memo.Memo;
import org.springframework.stereotype.Component;

@Component
public class MemoServiceImpl implements MemoService{

    @Override
    public Memo createMemo(String string) { //일단 문자열받아서 만드는거만 구현했음 아이디 받는거랑 메몽 아디 받는거 구현요망
        Memo memo = new Memo();
        memo.setData(string);

        return memo;
    }

    @Override
    public void deleteMemo(Memo memo) { //메모삭제구현요망

    }

    @Override
    public Memo modifyMemo(Memo memo) { //메모 수정 구현요망

        return memo;
    }
}
