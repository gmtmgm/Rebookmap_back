package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.memo.Memo;
import org.springframework.stereotype.Service;
import java.util.ArrayList;



@Service
public class BookMapServiceImpl implements BookMapService{

    public BookMapServiceImpl() {
    }
    @Override
    public BookMap createBookMap(Book book) { //새로 북맵을 만듦
        BookMap bookMap = new BookMap();
        addBook(addMap(bookMap), book);
        return bookMap;
    }

    //매번 new mapandmemo를 만들어서 목록에 집어넣는것 까지를 하나의 프로세스로 가정, 분리
    //기존 리스트에 추가하는것과 분리하기
    @Override
    public void addBook(ArrayList<Book> map, Book book) { map.add(book); }
    @Override
    public ArrayList<Book> addMap(BookMap bookMap) {
        ArrayList<Book> map = new ArrayList<Book>();
        bookMap.addObj(map);
        return map;
    }

    @Override
    public void addMemo(BookMap bookMap, Memo memo) { bookMap.addObj(memo); }

    @Override
    public void modiMap(BookMap bookMap, int index, Book book) { //이미 있는 책 줄 수정(책 추가)
        // 책이 1개 이상 존재한다는 가정이므로 삭제하여 0개가 되었을때를 고려해야함 < 책 모두 삭제되면 책 줄도 삭제되도록 만들기 (미구현 상태)
        //index: 수정할 부분
        ArrayList<Book> map = bookMap.getBookMapIndex().get(index).getMap();
        if (isMapNotFull(map)) {                    //5권 미만일때만 실행
            if (!map.contains(book)) {              //동일한 책이 없을 경우 추가
                bookMap.getBookMapIndex().get(index).getMap().add(book);
            }
            else {                                  //동일한 책이 같은 줄에 있을 경우 기존 위치에서 새로 입력한 위치(맨뒤)로 이동
                changeMapIndex(map, map.indexOf(book), map.size()-1);
            }
        }
        else
            System.out.println("한 줄에 최대 5권까지 등록할 수 있습니다."); //임시, 가독성 고려
    }

    @Override
    public void modiMemo(BookMap bookMap, int index, Memo memo) {
        bookMap.getBookMapIndex().get(index).setMemo(memo);
    }

    @Override
    public String checkType(BookMap bookMap, int index) { return bookMap.getBookMapIndex().get(index).getType(); }

    private boolean isMapNotFull(ArrayList<Book> map) { return map.size() < 5; }

    @Override
    public void deleteBookMap(BookMap bookMap) { bookMap = null; } //임시로 null 할당

    @Override
    public void deleteObj(BookMap bookMap, BookMap.MapAndMemo mapAndMemo, int index){
        bookMap.deleteObj(mapAndMemo, index);
    }
    @Override
    public void changeMapIndex(ArrayList<Book> map, int inputIndex, int outIndex) {
        Book changeBook = new Book();
        changeBook = map.get(inputIndex);
        map.remove(inputIndex);
        map.add(outIndex, changeBook);
    }
    @Override
    public void changeBookMapIndex(BookMap bookMap, int inputIndex, int outIndex){
        bookMap.changeIndex(inputIndex, outIndex);
    }
}
