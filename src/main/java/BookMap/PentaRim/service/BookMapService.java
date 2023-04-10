package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.memo.Memo;
import org.springframework.stereotype.Component;

@Component
public interface BookMapService {

    BookMap createBookMap(Book book);
    BookMap.MapAndMemo addBook(BookMap.MapAndMemo mapAndMemo, Book book);

    void addMemo(BookMap bookMap, Memo memo);

    void deleteBookMap(BookMap bookMap);

    void deleteObj(BookMap bookMap, BookMap.MapAndMemo mapAndMemo, int index);


    BookMap changeBookMapIndex(BookMap bookMap);


}
