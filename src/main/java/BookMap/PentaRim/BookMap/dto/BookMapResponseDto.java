package BookMap.PentaRim.BookMap.Dto;

import BookMap.PentaRim.BookMap.BookMapEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMapResponseDto {
    private Long bookMapId;
    private String bookMapTitle; //북맵이름
    private String bookMapContent; //북맵설명
    private String bookMapImage;
    private boolean share;
    //private List<String> hashTag;


    public BookMapResponseDto(BookMapEntity bookMapEntity, List<String> hashTag) {
        this.bookMapId = bookMapEntity.getBookMapId();
        this.bookMapTitle = bookMapEntity.getBookMapTitle();
        this.bookMapContent = bookMapEntity.getBookMapContent();
        this.bookMapImage = bookMapEntity.getBookMapImage();
        this.share = bookMapEntity.isShare();
        //if (bookMapEntity.getMapHashTags()!= null) { this.hashTag = hashTag; }
        //this.hashTag = hashTag;
    }


    public BookMapResponseDto(BookMapEntity bookMapEntity){
        this.bookMapTitle = bookMapEntity.getBookMapTitle();
        this.bookMapContent = bookMapEntity.getBookMapContent();
        this.bookMapImage = bookMapEntity.getBookMapImage();
        this.share = bookMapEntity.isShare();
    }


}