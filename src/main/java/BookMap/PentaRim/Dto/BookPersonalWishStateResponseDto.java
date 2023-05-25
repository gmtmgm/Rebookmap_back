package BookMap.PentaRim.Dto;

import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Book.BookState;
import BookMap.PentaRim.Book.Dto.BookResponseDto;
import lombok.Getter;

@Getter
public class BookPersonalWishStateResponseDto implements BookPersonalStateResponseDto {
    private Long id;
    private BookState bookState;
    private BookResponseDto bookResponseDto;
    public BookPersonalWishStateResponseDto(BookPersonal bookPersonal, BookResponseDto bookResponseDto){
        this.id = bookPersonal.getId();
        this.bookState = bookPersonal.getBookState();
        this.bookResponseDto = bookResponseDto;
    }
}
