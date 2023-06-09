package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Book.BookState;
import BookMap.PentaRim.Dto.BookShelfResponseDto;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookShelfReadingDto implements BookShelfResponseDto {
    private Long id;
    private String isbn;
    private BookState bookState;
    private String title;
    private String author;
    private String image;
    private LocalDate startDate;
    private Integer totalPage;
    private Integer readingPage;
    private Integer readingPercentage;

    public BookShelfReadingDto(BookPersonal bookPersonal){
        this.id = bookPersonal.getId();
        this.isbn = bookPersonal.getBook().getIsbn();
        this.bookState = bookPersonal.getBookState();
        this.title = bookPersonal.getBook().getTitle();
        this.author = bookPersonal.getBook().getAuthor();
        this.image = bookPersonal.getBook().getImage();
        this.startDate = bookPersonal.getStartDate();
        this.totalPage = bookPersonal.getTotalPage();
        this.readingPage = bookPersonal.getReadingPage();
        this.readingPercentage = (int) ((double)(readingPage*100)/(double)totalPage);
    }
}
