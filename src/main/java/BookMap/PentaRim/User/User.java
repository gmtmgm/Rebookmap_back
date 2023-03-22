package BookMap.PentaRim.User;


import BookMap.PentaRim.memo.Memo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {

    private Long id;
    private String NickName;
    private List<Memo> memoList;
}
