package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.memo.Memo;



public interface BookMapService {

    BookMap createBookMap(Book book);

    void addBook(BookMap.MapAndMemo mapAndMemo, Book book);

    void addMemo(BookMap bookMap, Memo memo);

    void deleteBookMap(BookMap bookMap);

    void deleteObj(BookMap bookMap, BookMap.MapAndMemo mapAndMemo, int index);

    void changeMapIndex(BookMap.MapAndMemo mapAndMemo, int inputIndex, int outIndex);

    void changeBookMapIndex(BookMap bookMap, int inputIndex, int outIndex);


}
