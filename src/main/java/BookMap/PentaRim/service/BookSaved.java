package BookMap.PentaRim.service;

import org.springframework.stereotype.Component;

@Component // 저장소와 연결하고 구현? 아니면 그냥 구현?
public interface BookSaved {

    void ReadComplete();
    void want();
    void ReadMemo();
}
