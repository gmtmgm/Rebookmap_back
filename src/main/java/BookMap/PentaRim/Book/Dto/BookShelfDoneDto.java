package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Book.BookState;
import BookMap.PentaRim.Dto.BookShelfResponseDto;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookShelfDoneDto implements BookShelfResponseDto {
    private Long id;
    private BookState bookState;
    private String title;
    private String author;
    private String image;
    private Float grade;
    private LocalDate startDate;
    private LocalDate endDate;

    public BookShelfDoneDto(BookPersonal bookPersonal){
        this.id = bookPersonal.getId();
        this.bookState = bookPersonal.getBookState();
        this.title = bookPersonal.getBook().getTitle();
        this.author = bookPersonal.getBook().getAuthor();
        this.image = bookPersonal.getBook().getImage();
        this.startDate = bookPersonal.getStartDate();
        this.grade = bookPersonal.getGrade();
        this.endDate = bookPersonal.getEndDate();
    }
}
