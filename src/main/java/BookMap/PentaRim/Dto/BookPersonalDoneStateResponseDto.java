package BookMap.PentaRim.Dto;

import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Book.BookState;
import BookMap.PentaRim.Book.Dto.BookMemoResponseDto;
import BookMap.PentaRim.Book.Dto.BookResponseDto;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class BookPersonalDoneStateResponseDto implements BookPersonalStateResponseDto {
    private Long id;
    private BookState bookState;
    private BookResponseDto bookResponseDto;
    private Float grade;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<BookMemoResponseDto> bookMemoResponseDtos;

    public BookPersonalDoneStateResponseDto(BookPersonal bookPersonal, BookResponseDto bookResponseDto,
                                            List<BookMemoResponseDto> bookMemoResponseDtos){
        this.id = bookPersonal.getId();
        this.bookState = bookPersonal.getBookState();
        this.bookResponseDto = bookResponseDto;
        this.grade = bookPersonal.getGrade();
        this.startDate = bookPersonal.getStartDate();
        this.endDate = bookPersonal.getEndDate();
        this.bookMemoResponseDtos = bookMemoResponseDtos;
    }
}
