package BookMap.PentaRim.BookMap.Dto;

import BookMap.PentaRim.BookMap.BookMapEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class BookMapResponseDto {

    private String bookMapTitle; //북맵이름
    private String bookMapContent; //북맵설명
    private String bookMapImage;

    private List<String> hashTag;
    private boolean share;


    public BookMapResponseDto(BookMapEntity bookMapEntity, List<String> hashTag) {
        this.bookMapTitle = bookMapEntity.getBookMapTitle();
        this.bookMapContent = bookMapEntity.getBookMapContent();
        this.bookMapImage = bookMapEntity.getBookMapImage();
        //if (bookMapEntity.getMapHashTags()!= null) { this.hashTag = hashTag; }
        this.hashTag = hashTag;
        this.share = bookMapEntity.isShare();
    }


    public BookMapResponseDto(BookMapEntity bookMapEntity){
        this.bookMapTitle = bookMapEntity.getBookMapTitle();
        this.bookMapContent = bookMapEntity.getBookMapContent();
        this.bookMapImage = bookMapEntity.getBookMapImage();
        this.share = bookMapEntity.isShare();
    }





}
