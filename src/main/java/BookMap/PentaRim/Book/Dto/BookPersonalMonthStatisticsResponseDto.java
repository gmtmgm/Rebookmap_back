package BookMap.PentaRim.Book.Dto;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class BookPersonalMonthStatisticsResponseDto implements Serializable {

    private List<BookPersonalMonthResponseDto> bookPersonalMonthResponseDto;

    private Integer totalBooks;
    private Integer totalReadingPages;
    private Integer totalDays;

    public BookPersonalMonthStatisticsResponseDto(List<BookPersonalMonthResponseDto> bookPersonalMonthResponseDto,
                                                  Integer totalBooks,
                                                  Integer totalReadingPages,
                                                  Integer totalDays){
        this.bookPersonalMonthResponseDto = bookPersonalMonthResponseDto;
        this.totalBooks = totalBooks;
        this.totalReadingPages = totalReadingPages;
        this.totalDays = totalDays;
    }
}
