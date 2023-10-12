package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.BookMemo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Setter
@Component
public class BookMemoRequestDto {
    private String content;
    private String title;
    private Integer page;

    @Builder
    public BookMemoRequestDto(String content, Integer page, String title){
        this.content = content;
        this.page = page;
        this.title = title;
    }

    public BookMemo toEntity(){
        return BookMemo.builder()
                .content(content)
                .page(page)
                .title(title)
                .build();
    }

}
