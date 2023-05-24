package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.BookMap;



import java.util.ArrayList;



public interface BookMapService {

    BookMap createBookMap(Book book);

    void addBook(ArrayList<Book> map, Book book);

    ArrayList<Book> addMap(BookMap bookMap);

    void addMemo(BookMap bookMap, String memo);

    void modiMap(BookMap bookMap, int index, Book book);

    void modiMemo(BookMap bookMap, int index, String memo);

    String checkType(BookMap bookMap, int index);

    void deleteBookMap(BookMap bookMap);

    void deleteObj(BookMap bookMap, BookMap.BookMapDetail bookMapDetail, int index);

    void changeMapIndex(ArrayList<Book> map, int inputIndex, int outIndex);

    void changeBookMapIndex(BookMap bookMap, int inputIndex, int outIndex);



}
