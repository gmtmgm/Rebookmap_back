package BookMap.PentaRim.BookMap.Dto;


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

    public BookMapDetailResponseDto(BookMapDetailEntity bookMapDetailEntity){
        this.bookMapDetailId = bookMapDetailEntity.getBookMapDetailId();
        this.bookMapEntity = bookMapDetailEntity.getBookMapEntity();
        this.type = bookMapDetailEntity.getType();
        if (type == "Memo") { this.memo = bookMapDetailEntity.getMemo(); }
        this.index = bookMapDetailEntity.getIndex();
    }
/*
    public void toBookMapDetail(BookMap.BookMapDetail bookMapDetail){
        bookMapDetail.setBookMapDetailId(bookMapDetailId);
        bookMapDetail.setType(type);
        bookMapDetail.setIndex(index);
        if (type == "Memo") { bookMapDetail.setMemo(memo); }
    }
 */
}