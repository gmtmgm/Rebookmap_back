package BookMap.PentaRim.Book;


import BookMap.PentaRim.memo.Memo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class BookPersonal {
    //이야기 해봐야함, book에 안넣는다면 따로 이런식으로?
    private Book book;
    private BookState bookState;
    private Memo memo;

}
