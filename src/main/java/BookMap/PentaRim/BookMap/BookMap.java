package BookMap.PentaRim.BookMap;


import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.memo.Memo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMapAdapter;

import java.util.ArrayList;
import java.util.Map;

@Component
@Getter
@Setter
public class BookMap {

    private Long userId;
    private String BookMapName;
    private Long bookMapId;
    private static Long serialNum = 0L;

    Long getSerialNum() {
        return serialNum;
    }

    @Component
    @Getter
    @Setter
    public class MapAndMemo {
        ArrayList<Book> map;
        private Memo memo;
        private String type;

        @Autowired
        MapAndMemo(){}

        MapAndMemo(ArrayList<Book> map){
            this.map = map;
            this.type = "Map";
        }

        MapAndMemo(Memo memo){
            this.memo = memo;
            this.type = "Memo";
        }
    }


    private ArrayList<MapAndMemo> bookMapIndex = new ArrayList<>();

    public BookMap(){ //생성 시 ID 증가
        bookMapId = serialNum;
        serialNum++;
    }

    //오버로딩 사용하여 객체 추가 메소드 이름 통일
    public void addObj(ArrayList<Book> map){
        MapAndMemo mapObj = new MapAndMemo(map);
        bookMapIndex.add(mapObj);
    }

    public void addObj(Memo memo){ //북맵에 메모 줄 추가
        MapAndMemo memoObj = new MapAndMemo(memo);
        bookMapIndex.add(memoObj);
    }

    public void deleteObj(MapAndMemo mapAndMemo, int index) {
        bookMapIndex.remove(index);
        mapAndMemo = null; //임시
    }

    //북맵 내부 순서 변경
    public void changeIndex(int inputIndex, int outIndex){
        MapAndMemo changeObj = new MapAndMemo();
        changeObj = bookMapIndex.get(inputIndex);
        bookMapIndex.remove(inputIndex);
        bookMapIndex.add(outIndex, changeObj);
    }

}

