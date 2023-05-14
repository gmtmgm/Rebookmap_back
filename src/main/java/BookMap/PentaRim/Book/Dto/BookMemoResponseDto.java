package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Book.BookMemo;
import BookMap.PentaRim.Book.BookPersonal;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookMemoResponseDto {
    private Long id;
    private Book book;
    private String content;
    private LocalDateTime saved;
    private Integer page;

    public BookMemoResponseDto(BookMemo bookMemo){
        this.id = bookMemo.getId();
        this.book = bookMemo.getBookPersonal().getBook();
        this.page = bookMemo.getPage();
        this.saved = bookMemo.getSaved();
        this.content = bookMemo.getContent();
    }

}
