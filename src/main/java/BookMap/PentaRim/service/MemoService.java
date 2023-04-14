package BookMap.PentaRim.service;

import BookMap.PentaRim.memo.Memo;
import org.springframework.stereotype.Component;

@Component
public interface MemoService {

    Memo createMemo(String string);

}
