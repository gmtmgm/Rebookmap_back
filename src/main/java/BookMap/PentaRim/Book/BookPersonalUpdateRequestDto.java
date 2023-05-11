package BookMap.PentaRim.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BookPersonalUpdateRequestDto {

    public BookState bookState;
    public LocalDate endDate;
    public Float grade;
    public Integer readingPage;
    public Integer bookPage;

    @Builder
    public BookPersonalUpdateRequestDto(BookState bookState, LocalDate endDate, Float grade, Integer readingPage, Integer bookPage){
        this.bookState = bookState;
        this.endDate = endDate;
        this.readingPage  = readingPage;
        this.bookPage = bookPage;
        this.grade = grade;
    }

}
