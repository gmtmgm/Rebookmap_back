package BookMap.PentaRim.Book.Dto;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class BookPersonalMonthStatisticsResponseDto implements Serializable {

    private List<BookPersonalMonthResponseDto> bookPersonalMonthResponseDto;
    private Integer totalBooks;
    private Integer totalReadingPages;

    public BookPersonalMonthStatisticsResponseDto(List<BookPersonalMonthResponseDto> bookPersonalMonthResponseDto,
                                                  Integer totalBooks,
                                                  Integer totalReadingPages){
        this.bookPersonalMonthResponseDto = bookPersonalMonthResponseDto;
        this.totalBooks = totalBooks;
        this.totalReadingPages = totalReadingPages;
    }
}
