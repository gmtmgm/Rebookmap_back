package BookMap.PentaRim.Book;


import BookMap.PentaRim.memo.Memo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class BookPersonal {

    private boolean readOrNot;
    private boolean wantOrNot;
    private Memo memo;
}
