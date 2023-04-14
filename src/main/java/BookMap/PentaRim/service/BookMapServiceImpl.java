package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.memo.Memo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class BookMapServiceImpl implements BookMapService{

    @Override
    public BookMap createBookMap(Book book) { //책을 반드시 한 권 이상 선택하여 북맵 만들도록
        //북맵 이름을 받고 시작할 것인지? //받고 시작
        BookMap bookMap = new BookMap();
        BookMap.MapAndMemo bookList = bookMap.new MapAndMemo();
        addBook(bookList, book);
        bookMap.addObj(bookList.getMap());
        return bookMap;
    }

    @Override
    public void addBook(BookMap.MapAndMemo mapAndMemo, Book book) {
        //책 추가를 무조건 맨 뒤에 오게 했는데 따로 원하는 위치에 추가하게 할지 상의 필요//맨 뒤가 나을거같아요
        ArrayList<Book> map = mapAndMemo.getMap();
        if (isMapNotFull(map)) {                    //5권 미만일때만 실행
            if (!map.contains(book)) {              //동일한 책이 없을 경우 추가
                mapAndMemo.addObj(book);
            }
            else {                                  //동일한 책이 같은 줄에 있을 경우 기존 위치에서 새로 입력한 위치(맨뒤)로 이동
                changeMapIndex(mapAndMemo, map.indexOf(book), map.size()-1);
            }
        }
        else
            System.out.println("한 줄에 최대 5권까지 등록할 수 있습니다."); //임시, 가독성 고려
    }


    @Override
    public void addMemo(BookMap bookMap, Memo memo) { bookMap.addObj(memo); }

    private boolean isMapNotFull(ArrayList<Book> map) { return map.size() < 5; }

    @Override
    public void deleteBookMap(BookMap bookMap) { bookMap = null; } //임시로 null 할당

    @Override
    public void deleteObj(BookMap bookMap, BookMap.MapAndMemo mapAndMemo, int index){
        bookMap.deleteObj(mapAndMemo, index);
    }
    @Override
    public void changeMapIndex(BookMap.MapAndMemo mapAndMemo, int inputIndex, int outIndex) {
        mapAndMemo.changeIndex(inputIndex, outIndex);
    }
    @Override
    public void changeBookMapIndex(BookMap bookMap, int inputIndex, int outIndex){
        bookMap.changeIndex(inputIndex, outIndex);
    }
}
