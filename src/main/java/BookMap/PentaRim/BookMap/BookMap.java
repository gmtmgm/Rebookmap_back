package BookMap.PentaRim.BookMap;


import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.memo.Memo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMapAdapter;

import java.util.ArrayList;
import java.util.Map;

@Component
@Getter
@Setter
public class BookMap {

    private Long userId;
    private Long isbn; //꼭 필요한지 //책 식별용도로 필요함
    private String BookMapName;
    private Long bookMapId;
    private static Long serialNum = 0L;

    Long getSerialNum() {
        return serialNum;
    }

    @Getter
    @Setter
    public class MapAndMemo{        //하나의 클래스로 통일되었으므로 어떤게 책이고 메모인지 구별하는 작업 필요
        private ArrayList<Book> map;
        private Memo memo;
        public void addObj(Book book){
            map.add(book);
        } //책 줄에 책 추가
        public ArrayList<Book> getMap(){
            return map;
        }
        public void changeIndex(int inputIndex, int outIndex){ //책 줄 내에서 순서 변경
            //input: 현재순서, output: 바뀔순서
            Book changeBook = map.get(inputIndex);
            map.remove(inputIndex);
            map.add(outIndex, changeBook);
        }
    }


    private ArrayList<MapAndMemo> bookMapIndex = new ArrayList<>();


    public BookMap(){ //생성 시 ID 증가 (테스트 필요)
        bookMapId = serialNum;
        serialNum++;
    }

    //오버로딩 사용하여 객체 추가 메소드 이름 통일
    public void addObj(ArrayList<Book> map){ //북맵에 책 줄 추가
        MapAndMemo mapObj = new MapAndMemo();
        mapObj.map = map;
        bookMapIndex.add(mapObj);
    }

    public void addObj(Memo memo){ //북맵에 메모 줄 추가
        MapAndMemo memoObj = new MapAndMemo();
        memoObj.memo = memo;
        bookMapIndex.add(memoObj);
    }

    public void deleteObj(MapAndMemo mapAndMemo, int index) {
        bookMapIndex.remove(index);
        mapAndMemo = null; //임시
    }

    //북맵 내부 순서 변경
    public void changeIndex(int inputIndex, int outIndex){
        MapAndMemo changeObj = bookMapIndex.get(inputIndex);
        bookMapIndex.remove(inputIndex);
        bookMapIndex.add(outIndex, changeObj);
    }

}

