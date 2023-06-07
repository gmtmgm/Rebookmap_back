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

    public BookImageDto(Book book){
        this.id = book.getId();
        this.isbn = book.getIsbn();
        this.image = book.getImage();
    }
}
