package BookMap.PentaRim.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookMemoUpdateRequestDto {

    public String content;
    public Integer page;

    @Builder
    public BookMemoUpdateRequestDto(String content, Integer page){
        this.content = content;
        this.page = page;
    }

}
