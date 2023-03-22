package BookMap.PentaRim.BookMap;


import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.memo.Memo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMap {

    private Long userId;
    private Long isbn;
    private List<Book> map;
    private List<Memo> memo;
    private String BookMapName;
    private Long bookMapId;


    public void addBookToMap(BookMap bookMap, Book book) {
        bookMap.map.add(book);
    }

    public void addMemoToMemo(BookMap bookMap, Memo memo) {
        bookMap.memo.add(memo);
    }


}
