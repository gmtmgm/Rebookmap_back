package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Dto.BookPersonalStateResponseDto;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class SearchBookResponseDto implements BookPersonalStateResponseDto {
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishedDay;
    private String isbn;
    private String image;
    private String description;

    public SearchBookResponseDto(Book book){
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.publishedDay = book.getPublishedDay();
        this.isbn = book.getIsbn();
        this.image = book.getImage();
        this.description = book.getDescription();
    }
}
