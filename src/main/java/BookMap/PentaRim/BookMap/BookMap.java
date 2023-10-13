package BookMap.PentaRim.BookMap;


import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Book.Dto.BookImageDto;
import BookMap.PentaRim.User.model.User;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;


@Getter
@Setter
public class BookMap implements Serializable {


    private Long bookMapId;
    @JsonIgnore
    private User user;
    private String bookMapTitle; //북맵이름
    private String bookMapContent; //북맵설명
    private String bookMapImage;
    private List<String> hashTag; //북맵 하나에 해쉬태그여러개를 붙이는 형식
    //private int index;
    private boolean share = true; //북맵 잠금 여부
    @JsonIgnore
    private LocalDateTime bookMapSaveTime; // 저장 시간
    private String nickname;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class BookMapDetail {
        @JsonIgnore
        private Long bookMapDetailId;
        private String type; //부모클래스 리스트에 넣은 후 자식클래스 메소드 어떻게 해야 할지 모르겠어서 일단 type 설정
//        private ArrayList<Book> map;
        private ArrayList<BookImageDto> map;
        private String memo;

        @JsonIgnore
        private int index;

    }

    @Getter
    @Setter
    public static class BookMapBook extends BookMapDetail {
        public BookMapBook(){ super.setType("Book"); }
//        public void setMap(ArrayList<Book> map){
//            super.map = map;
//        }
        public void setMap(ArrayList<BookImageDto> map){
            super.map = map;
        }
    }

    @Getter
    @Setter
    public static class BookMapMemo extends BookMapDetail {
        public BookMapMemo(){ super.setType("Memo"); }
        public void setMemo(String memo) {
            super.memo = memo;
        }
    }

    private ArrayList<BookMapDetail> bookMapIndex = new ArrayList<>();

    //오버로딩 사용하여 객체 추가 메소드 이름 통일
//    public void addObj(ArrayList<Book> map, Long bookMapDetailId){
//        BookMapBook mapObj = new BookMapBook();
//        mapObj.setBookMapDetailId(bookMapDetailId);
//        mapObj.setMap(map);
//        bookMapIndex.add(mapObj);
//    }
    public void addObj(ArrayList<BookImageDto> map, Long bookMapDetailId){
        BookMapBook mapObj = new BookMapBook();
        mapObj.setBookMapDetailId(bookMapDetailId);
        mapObj.setMap(map);
        bookMapIndex.add(mapObj);
    }
    public void addObj(String memo, Long bookMapDetailId){ //북맵에 메모 줄 추가
        BookMapMemo memoObj = new BookMapMemo();
        memoObj.setBookMapDetailId(bookMapDetailId);
        memoObj.setMemo(memo);
        bookMapIndex.add(memoObj);
    }

}

