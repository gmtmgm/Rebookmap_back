package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Book.BookState;
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
    private Float grade;

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
