package BookMap.PentaRim.Dto;

import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Book.BookState;
import BookMap.PentaRim.Book.Dto.BookMemoResponseDto;
import BookMap.PentaRim.Book.Dto.BookResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class BookPersonalWishStateResponseDto implements BookPersonalStateResponseDto {
    private Long id;
    private BookState bookState;
    private BookResponseDto bookResponseDto;
    private List<BookMemoResponseDto> bookMemoResponseDtos;
    public BookPersonalWishStateResponseDto(BookPersonal bookPersonal, BookResponseDto bookResponseDto,
                                            List<BookMemoResponseDto> bookMemoResponseDtos){
        this.id = bookPersonal.getId();
        this.bookState = bookPersonal.getBookState();
        this.bookResponseDto = bookResponseDto;
        this.bookMemoResponseDtos = bookMemoResponseDtos;
    }
}
