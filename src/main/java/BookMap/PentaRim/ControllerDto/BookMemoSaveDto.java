package BookMap.PentaRim.ControllerDto;

import BookMap.PentaRim.Book.Dto.BookMemoRequestDto;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

@Data
public class BookMemoSaveDto {
    String isbn;
    BookMemoRequestDto bookMemoRequestDto;
    String sessionId;
}
