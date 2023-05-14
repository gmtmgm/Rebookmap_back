package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.BookState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BookPersonalUpdateRequestDto {

    public BookState bookState;
    public LocalDate startDate;
    public LocalDate endDate;
    public Float grade;
    public Integer readingPage;
    public Integer totalPage;

    @Builder
    public BookPersonalUpdateRequestDto(BookState bookState,
                                        LocalDate startDate,
                                        LocalDate endDate,
                                        Float grade,
                                        Integer readingPage,
                                        Integer bookPage){
        this.bookState = bookState;
        this.startDate = startDate;
        this.endDate = endDate;
        this.readingPage  = readingPage;
        this.totalPage = bookPage;
        this.grade = grade;
    }



}
