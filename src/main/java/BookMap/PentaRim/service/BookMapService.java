package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.memo.Memo;
import org.springframework.stereotype.Component;

@Component
public interface BookMapService {

    BookMap createBookMap(Book book);

    BookMap addBook(BookMap bookMap, Book book); //일단 메모 따로 책 객체 따로 받게 만들었음 이거 추후에 정렬하는거 고민해야됨.

    BookMap addMemo(BookMap bookMap, Memo memo);

    void deleteBookMap(BookMap bookMap);

}
