package BookMap.PentaRim.Dto;

import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Book.BookState;
import BookMap.PentaRim.Book.Dto.BookMemoResponseDto;
import BookMap.PentaRim.Book.Dto.BookResponseDto;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class BookPersonalReadingStateResponseDto implements BookPersonalStateResponseDto {

    private Long id;
    private BookState bookState;
    private BookResponseDto bookResponseDto;

    private LocalDate startDate;
    private Integer totalPage;
    private Integer readingPage;
    private Integer readingPercentage;
    private List<BookMemoResponseDto> bookMemoResponseDtos;

    public BookPersonalReadingStateResponseDto(BookPersonal bookPersonal, BookResponseDto bookResponseDto,
                                               List<BookMemoResponseDto> bookMemoResponseDtos){
        this.id = bookPersonal.getId();
        this.bookState = bookPersonal.getBookState();
        this.bookResponseDto = bookResponseDto;
        this.startDate = bookPersonal.getStartDate();
        this.totalPage = bookPersonal.getTotalPage();
        this.readingPage = bookPersonal.getReadingPage();
        this.readingPercentage = (int) ((double)(readingPage*100)/(double)totalPage);
        this.bookMemoResponseDtos = bookMemoResponseDtos;
    }

}
