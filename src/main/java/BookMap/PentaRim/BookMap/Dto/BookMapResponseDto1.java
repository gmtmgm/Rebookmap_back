package BookMap.PentaRim.BookMap.Dto;

import BookMap.PentaRim.BookMap.BookMapEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class BookMapResponseDto1 {

    private String bookMapTitle; //북맵이름
    private String bookMapContent; //북맵설명
    private List<String> hashTag;


    public BookMapResponseDto1(BookMapEntity bookMapEntity, List<String> hashTag) {
        this.bookMapTitle = bookMapEntity.getBookMapTitle();
        this.bookMapContent = bookMapEntity.getBookMapContent();
        //if (bookMapEntity.getMapHashTags()!= null) { this.hashTag = hashTag; }
        this.hashTag = hashTag;
    }


    public BookMapResponseDto1(BookMapEntity bookMapEntity){
        this.bookMapTitle = bookMapEntity.getBookMapTitle();
        this.bookMapContent = bookMapEntity.getBookMapContent();
    }





}
