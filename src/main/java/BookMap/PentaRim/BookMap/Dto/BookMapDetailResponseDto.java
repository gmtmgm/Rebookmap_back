package BookMap.PentaRim.BookMap.Dto;


import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.BookMap.BookMapDetailEntity;
import BookMap.PentaRim.BookMap.BookMapEntity;
import lombok.Getter;

@Getter
public class BookMapDetailResponseDto {

    private Long bookMapDetailId;
    private BookMapEntity bookMapEntity;
    private String type;
    private String memo;
    private int index;

    private String nickname;

    public BookMapDetailResponseDto(BookMapDetailEntity bookMapDetailEntity){
        this.bookMapDetailId = bookMapDetailEntity.getBookMapDetailId();
        this.bookMapEntity = bookMapDetailEntity.getBookMapEntity();
        this.type = bookMapDetailEntity.getType();
        if (type == "Memo") { this.memo = bookMapDetailEntity.getMemo(); }
        this.index = bookMapDetailEntity.getIndex();
        this.nickname = bookMapEntity.getUser().getNickname();
    }

}