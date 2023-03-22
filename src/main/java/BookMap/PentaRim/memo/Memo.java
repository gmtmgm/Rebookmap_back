package BookMap.PentaRim.memo;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Memo {

    private String userId;
    private Long isbn;
    private Long MemoId;
    private String Data;
}
