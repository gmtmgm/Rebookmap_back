package BookMap.PentaRim.BookMap;


import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.User.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;


@Component
@Getter
@Setter
public class BookMap implements Serializable {


    private Long bookMapId;

    private User user;
    private String bookMapTitle; //북맵이름
    private String bookMapContent; //북맵설명
    private String bookMapImage;
    private List<String> hashTag; //북맵 하나에 해쉬태그여러개를 붙이는 형식
    //private int index;
    private boolean share = true; //북맵 잠금 여부

    @Component
    @Getter
    @Setter
    public class BookMapDetail {
        private Long bookMapDetailId;
        private String type; //부모클래스 리스트에 넣은 후 자식클래스 메소드 어떻게 해야 할지 모르겠어서 일단 type 설정
        private LinkedHashMap<Long, Book> map;
        private String memo;
        private int index;

    }

    public class BookMapBook extends BookMapDetail {
        public void setMap(LinkedHashMap<Long, Book> map){
            super.map = map;
            super.setType("Book");
        }

    }

    public class BookMapMemo extends BookMapDetail {
        public void setMemo(String memo) {
            super.memo = memo;
            super.setType("Memo");
        }
    }
    private ArrayList<BookMapDetail> bookMapIndex = new ArrayList<>();

    //오버로딩 사용하여 객체 추가 메소드 이름 통일
    public void addObj(LinkedHashMap<Long, Book> map){
        BookMapBook mapObj = new BookMapBook();
        mapObj.setMap(map);
        bookMapIndex.add(mapObj);
    }

    public void addObj(String memo){ //북맵에 메모 줄 추가
        BookMapMemo memoObj = new BookMapMemo();
        memoObj.setMemo(memo);
        bookMapIndex.add(memoObj);
    }

    public void deleteObj(BookMapDetail bookMapDetail, int index) {
        bookMapIndex.remove(index);
        bookMapDetail = null; //임시
    }

    //북맵 내부 순서 변경
    public void changeIndex(int inputIndex, int outIndex){
        BookMapDetail changeObj = new BookMapDetail();
        changeObj = bookMapIndex.get(inputIndex);
        bookMapIndex.remove(inputIndex);
        bookMapIndex.add(outIndex, changeObj);
    }
}

