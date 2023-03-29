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
        bookMap = null;     //임시로 null 할당하긴 했는데 상의 필요
    }

    @Override
    public void deleteBook(BookMap bookMap, Book book){
        bookMap.deleteBookFromMap(bookMap, book);
    }

    @Override
    public void deleteMemo(BookMap bookMap, Memo memo){
        bookMap.deleteMemoFromMap(bookMap, memo);
    }

    public void changeBookMapIndex(BookMap bookMap){

    }

}
