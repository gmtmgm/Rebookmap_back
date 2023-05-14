package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.BookState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class BookPersonalUpdateStateDto {
    public BookState bookState;

    @Builder
    public BookPersonalUpdateStateDto(BookState bookState){
        this.bookState = bookState;
    }


}
