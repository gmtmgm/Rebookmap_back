package BookMap.PentaRim.BookMapControllerDto;

import BookMap.PentaRim.BookMap.Dto.BookMapSaveRequestDto;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

@Data
public class BookMapSaveDto {

    BookMapSaveRequestDto bookMapSaveRequestDto;
    String sessionId;
}
