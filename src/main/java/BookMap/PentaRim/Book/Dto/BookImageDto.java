package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.Book;
import lombok.Getter;

@Getter
public class BookImageDto {
    private Long id;
    private String image;

    public BookImageDto(Book book){
        this.id = book.getId();
        this.image = book.getImage();
    }
}
