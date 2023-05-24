package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.BookMemo;
import lombok.Getter;

@Getter
public class ProfileMemoResponseDto {
    private Long id;
    private String title;
    private String content;

    public ProfileMemoResponseDto(BookMemo bookMemo){
        this.id = bookMemo.getId();
        this.title = bookMemo.getBookPersonal().getBook().getTitle();
        this.content = bookMemo.getContent();
    }
}
