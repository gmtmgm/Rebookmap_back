package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.memo.Memo;

import java.util.ArrayList;
import java.util.List;

public class BookMapServiceImpl implements BookMapService{

    @Override
    public BookMap createBookMap(Book book) {
       BookMap bookMap = new BookMap();
        List<Book> list = new ArrayList<>();
        list.add(book);
        bookMap.setMap(list);
       return bookMap;
    }

    @Override
    public BookMap addBook(BookMap bookMap, Book book) {
        bookMap.addBookToMap(bookMap, book);
        return bookMap;
    }

    @Override
    public BookMap addMemo(BookMap bookMap, Memo memo) {
        bookMap.addMemoToMemo(bookMap, memo);
        return bookMap;
    }

    @Override
    public void deleteBookMap(BookMap bookMap) {
        //구현필요
    }
}
