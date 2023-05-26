package BookMap.PentaRim.BookMap.Dto;

import BookMap.PentaRim.BookMap.BookMapEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class BookMapResponseDto {

    private String bookMapTitle; //북맵이름
    private String bookMapContent; //북맵설명
    private List<String> hashTag;


    public BookMapResponseDto(BookMapEntity bookMapEntity, List<String> hashTag) {
        this.bookMapTitle = bookMapEntity.getBookMapTitle();
        this.bookMapContent = bookMapEntity.getBookMapContent();
        //if (bookMapEntity.getMapHashTags()!= null) { this.hashTag = hashTag; }
        this.hashTag = hashTag;
    }


    public BookMapResponseDto(BookMapEntity bookMapEntity){
        this.bookMapTitle = bookMapEntity.getBookMapTitle();
        this.bookMapContent = bookMapEntity.getBookMapContent();
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