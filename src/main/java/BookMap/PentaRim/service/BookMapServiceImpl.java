package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.memo.Memo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapServiceImpl implements BookMapService{

    @Override
    public BookMap createBookMap(Book book) {
        BookMap bookMap = new BookMap();
        BookMap.MapAndMemo list = bookMap.new MapAndMemo();
        addBook(list, book);
        bookMap.addObj(bookMap, list.getMap());
        return bookMap;
    }

    @Override
    public BookMap.MapAndMemo addBook(BookMap.MapAndMemo mapAndMemo, Book book) {
        mapAndMemo.addObj(book);
        return mapAndMemo;
    }

    @Override
    public void addMemo(BookMap bookMap, Memo memo) { bookMap.addObj(bookMap, memo); }

    @Override
    public void deleteBookMap(BookMap bookMap) { bookMap = null; } //임시로 null 할당하긴 했는데 상의 필요

    @Override
    public void deleteObj(BookMap bookMap, BookMap.MapAndMemo mapAndMemo, int index){
        bookMap.deleteObj(bookMap, mapAndMemo, index);
    }

    public BookMap changeBookMapIndex(BookMap bookMap){
        //미구현
        return bookMap;
    }
}
