package BookMap.PentaRim.Book;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookPersonalResponseDto {
    private Long id;

    private Book book;

    private BookState bookState;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer readingPage;
    private Integer totalPage;
    private float grade;

    public BookPersonalResponseDto(BookPersonal bookPersonal){
        this.id = bookPersonal.getId();
        this.book = bookPersonal.getBook();
        this.startDate = bookPersonal.getStartDate();
        this.endDate = bookPersonal.getEndDate();
        this.readingPage = bookPersonal.getReadingPage();
        this.totalPage = bookPersonal.getTotalPage();
        this.bookState = bookPersonal.getBookState();
        this.grade= bookPersonal.getGrade();
    }

}
