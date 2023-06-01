package BookMap.PentaRim.BookMap.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@NoArgsConstructor
public class TagRequestDto {
    private List<String> tags;
    public TagRequestDto(List<String> tags){
        this.tags = tags;
    }
}
