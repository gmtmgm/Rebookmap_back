package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.BookMemo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookMemoResponseDto {
    private Long id;
    private String content;
    private LocalDateTime saved;
    private Integer page;
    private String title;

    public BookMemoResponseDto(BookMemo bookMemo){
        this.id = bookMemo.getId();
        this.page = bookMemo.getPage();
        this.saved = bookMemo.getSaved();
        this.content = bookMemo.getContent();
        this.title = bookMemo.getTitle();
    }

}
