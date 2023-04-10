package BookMap.PentaRim.BookMap;


import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.memo.Memo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMapAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
public class BookMap {

    private Long userId;
    private Long isbn;
    private String BookMapName;
    private Long bookMapId;
    public class MapAndMemo{
        private static ArrayList<Book> map;
        private static Memo memo;
        public void addObj(Book book){
            MapAndMemo.map.add(book);
        }
        public ArrayList<Book> getMap(){
            return MapAndMemo.map;
        }
    }
    private ArrayList<MapAndMemo> bookMapIndex = new ArrayList<>();



    //오버로딩 사용하여 객체 추가 함수 이름 통일
    public void addObj(BookMap bookMap, ArrayList<Book> map){
        MapAndMemo mapAdd = new MapAndMemo();
        mapAdd.map = map;
        bookMap.bookMapIndex.add(mapAdd);
    }
    public void addObj(BookMap bookMap, Memo memo){
        MapAndMemo memoAdd = new MapAndMemo();
        memoAdd.memo = memo;
        bookMap.bookMapIndex.add(memoAdd);
    }

    public void deleteObj(BookMap bookMap, MapAndMemo mapAndMemo, int index) {
        bookMap.bookMapIndex.remove(index);
        mapAndMemo = null;
    }

    public Boolean isMapFull(ArrayList<Book> map) { return map.size() < 5; } //한 줄에 책이 5권 이상 들어갔는지 확인

    public void changeIndex(BookMap bookMap, int inputIndex){ //flutter에서 순서 반환하는 방식 알아보기

    }

}
