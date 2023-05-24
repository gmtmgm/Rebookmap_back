package BookMap.PentaRim.Dto;

import BookMap.PentaRim.Book.Dto.BookImageDto;
import BookMap.PentaRim.Book.Dto.BookTopResponseDto;
import BookMap.PentaRim.BookMap.dto.BookMapResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MainResponseDto {
    private List<BookImageDto> bookImageDto;
    private List<BookMapResponseDto> bookMapResponseDtos;
    private List<BookTopResponseDto> bookTopResponseDtos;

    public MainResponseDto(List<BookImageDto> bookImageDto, List<BookMapResponseDto> bookMapResponseDtos,
                           List<BookTopResponseDto> bookTopResponseDtos ){
        this.bookImageDto = bookImageDto;
        this.bookMapResponseDtos = bookMapResponseDtos;
        this.bookTopResponseDtos = bookTopResponseDtos;

    }

}
