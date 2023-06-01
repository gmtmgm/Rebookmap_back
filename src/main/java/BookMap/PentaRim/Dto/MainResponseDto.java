package BookMap.PentaRim.Dto;

import BookMap.PentaRim.Book.Dto.BookImageDto;
import BookMap.PentaRim.Book.Dto.BookTopResponseDto;
import BookMap.PentaRim.BookMap.Dto.BookMapResponseDto1;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MainResponseDto {
    private List<BookImageDto> bookImageDto;
    private List<BookMapResponseDto1> bookMapResponseDtos;
    private List<BookTopResponseDto> bookTopResponseDtos;

    public MainResponseDto(List<BookImageDto> bookImageDto, List<BookMapResponseDto1> bookMapResponseDtos,
                           List<BookTopResponseDto> bookTopResponseDtos ){
        this.bookImageDto = bookImageDto;
        this.bookMapResponseDtos = bookMapResponseDtos;
        this.bookTopResponseDtos = bookTopResponseDtos;

    }

}
