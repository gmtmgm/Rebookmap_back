package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.BookMemo;
import lombok.Getter;

@Getter
public class ProfileMemoResponseDto {
    private Long id;
    private String bookTitle;
    private String bookAuthor;
    private Integer page;
    private String title;
    private String content;
    private String image;
    private String isbn;

    public ProfileMemoResponseDto(BookMemo bookMemo){
        this.id = bookMemo.getId();
        this.bookTitle = bookMemo.getBookPersonal().getBook().getTitle();
        this.content = bookMemo.getContent();
        this.bookAuthor = bookMemo.getBookPersonal().getBook().getAuthor();
        this.page = bookMemo.getPage();
        this.title = bookMemo.getTitle();
        this.image = bookMemo.getBookPersonal().getBook().getImage();
        this.isbn = bookMemo.getBookPersonal().getBook().getIsbn();
    }
}
