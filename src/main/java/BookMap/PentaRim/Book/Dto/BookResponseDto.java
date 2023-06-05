package BookMap.PentaRim.Book.Dto;


import BookMap.PentaRim.Book.Book;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishedDay;
    private String isbn;
    private String image;
    private String description;

    public BookResponseDto(Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.publishedDay = book.getPublishedDay();
        this.isbn = book.getIsbn();
        this.image = book.getImage();
        this.description = book.getDescription();
    }
}
