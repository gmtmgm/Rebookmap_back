package BookMap.PentaRim.BookMap.Dto;

import BookMap.PentaRim.BookMap.BookMapEntity;
import BookMap.PentaRim.User.User;
import lombok.Getter;

import java.util.HashSet;

@Getter
public class BookMapResponseDto {
    private User user;
    private String bookMapTitle; //북맵이름
    private String bookMapContent; //북맵설명
    private HashSet<String> hashTag;


    public BookMapResponseDto(BookMapEntity bookMapEntity) {
        this.user = bookMapEntity.getUser();
        this.bookMapTitle = bookMapEntity.getBookMapTitle();
        this.bookMapContent = bookMapEntity.getBookMapContent();
        if (bookMapEntity.getHashTag() != null) { this.hashTag = bookMapEntity.getHashTag(); }
    }
/*
    public void toBookMap(BookMap bookMap){
        bookMap.setBookMapId(bookMapId);
        bookMap.setUser(user);
        bookMap.setBookMapTitle(bookMapTitle);
        bookMap.setBookMapContent(bookMapContent);
        if (hashTag != null) {
            bookMap.setHashTag(hashTag);
        }
    }
 */

}