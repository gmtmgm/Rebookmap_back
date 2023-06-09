package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Book.BookState;
import BookMap.PentaRim.Dto.BookShelfResponseDto;
import lombok.Getter;

@Getter
public class BookShelfWishDto implements BookShelfResponseDto {
    private Long id;
    private String isbn;
    private BookState bookState;
    private String title;
    private String author;
    private String image;
    public BookShelfWishDto(BookPersonal bookPersonal){
        this.id = bookPersonal.getId();
        this.isbn = bookPersonal.getBook().getIsbn();
        this.bookState = bookPersonal.getBookState();
        this.title = bookPersonal.getBook().getTitle();
        this.author = bookPersonal.getBook().getAuthor();
        this.image = bookPersonal.getBook().getImage();
    }
}
