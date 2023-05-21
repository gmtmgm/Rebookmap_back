package BookMap.PentaRim.BookMap.dto;

import lombok.Getter;

@Getter
public class BookMapResponseDto {
    Long id;

    public BookMapResponseDto(Long id){
        this.id = id;
    }
}
