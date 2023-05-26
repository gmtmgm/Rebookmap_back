package BookMap.PentaRim.BookMap.Dto;

import lombok.Getter;

import java.util.List;
@Getter
public class TagRequestDto {
    private List<String> tags;
    public TagRequestDto() {
        // 기본 생성자
    }

    public TagRequestDto(List<String> tags){
        this.tags = tags;
    }
}
