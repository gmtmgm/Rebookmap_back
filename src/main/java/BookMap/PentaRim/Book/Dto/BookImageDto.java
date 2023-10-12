package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookImageDto {
    private Long id;
    private String isbn;
    private String image;
    private String title;
    private String author;


    public BookImageDto(Book book){
        this.id = book.getId();
        this.isbn = book.getIsbn();
        this.image = book.getImage();
        this.title = book.getTitle();
        this.author = book.getAuthor();
    }
}
