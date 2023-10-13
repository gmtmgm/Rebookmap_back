package BookMap.PentaRim.BookMap.Dto;

import BookMap.PentaRim.BookMap.BookMap;
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

    private List<String> hashTag;
    private boolean share;
    private String nickname;


    public BookMapResponseDto(BookMapEntity bookMapEntity, List<String> hashTag) {
        this.bookMapId = bookMapEntity.getBookMapId();
        this.bookMapTitle = bookMapEntity.getBookMapTitle();
        this.bookMapContent = bookMapEntity.getBookMapContent();
        this.bookMapImage = bookMapEntity.getBookMapImage();
        //if (bookMapEntity.getMapHashTags()!= null) { this.hashTag = hashTag; }
        this.hashTag = hashTag;
        this.share = bookMapEntity.isShare();
        this.nickname = bookMapEntity.getUser().getNickname();
    }


    public BookMapResponseDto(BookMapEntity bookMapEntity){
        this.bookMapId = bookMapEntity.getBookMapId();
        this.bookMapTitle = bookMapEntity.getBookMapTitle();
        this.bookMapContent = bookMapEntity.getBookMapContent();
        this.bookMapImage = bookMapEntity.getBookMapImage();
        this.share = bookMapEntity.isShare();
        this.nickname = bookMapEntity.getUser().getNickname();
    }

    public void toBookMap(BookMap bookMap){
        bookMap.setBookMapTitle(this.bookMapTitle);
        bookMap.setBookMapContent(this.bookMapContent);
        bookMap.setBookMapImage(this.bookMapImage);
        bookMap.setHashTag(this.hashTag);
        bookMap.setShare(this.share);
        bookMap.setNickname(this.nickname);

    }





}
