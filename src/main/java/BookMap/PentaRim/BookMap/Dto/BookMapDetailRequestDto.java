package BookMap.PentaRim.BookMap.Dto;

import BookMap.PentaRim.BookMap.BookMapDetailEntity;
import BookMap.PentaRim.BookMap.BookMapEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@NoArgsConstructor
public class BookMapDetailRequestDto {

    private BookMapEntity bookMapEntity;
    private String type;
    private String memo;
    private int index;
/*
    @Builder
    public BookMapDetailRequestDto(Long bookMapDetailId, BookMapEntity bookMapEntity, String type, String memo, int index){
        this.bookMapDetailId = bookMapDetailId;
        this.bookMapEntity = bookMapEntity;
        this.type = type;
        this.memo = memo;
        this.index = index;
    }
 */
    @Builder
    public BookMapDetailRequestDto(/*BookMapEntity bookMapEntity, */String type, String memo, int index) {
//        this.bookMapEntity = bookMapEntity;
        this.type = type;
        this.memo = memo;
        this.index = index;
    }

    public BookMapDetailEntity toEntity(){
        return BookMapDetailEntity.builder()
                .bookMapEntity(bookMapEntity)
                .type(type)
                .memo(memo)
                .index(index)
                .build();
    }

}