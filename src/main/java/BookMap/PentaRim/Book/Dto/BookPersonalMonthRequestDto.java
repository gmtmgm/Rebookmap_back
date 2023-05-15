package BookMap.PentaRim.Book.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@Setter
@Component
public class BookPersonalMonthRequestDto {
    private Integer year;
    private Integer month;

    @Builder
    public BookPersonalMonthRequestDto(Integer year, Integer month){
        this.year = year;
        this.month = month;
    }
}
