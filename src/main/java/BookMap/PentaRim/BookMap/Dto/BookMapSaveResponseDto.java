package BookMap.PentaRim.BookMap.Dto;

import BookMap.PentaRim.BookMap.BookMapEntity;
import BookMap.PentaRim.User.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMapSaveResponseDto {
    private Long bookMapId;
    private String bookMapTitle; //북맵이름
    private String bookMapContent; //북맵설명
    private User user;
    //private List<String> hashTag;


    public BookMapSaveResponseDto(BookMapEntity bookMapEntity) {
        this.bookMapId = bookMapEntity.getBookMapId();
        this.bookMapTitle = bookMapEntity.getBookMapTitle();
        this.bookMapContent = bookMapEntity.getBookMapContent();
        this.user = bookMapEntity.getUser();
    }


}