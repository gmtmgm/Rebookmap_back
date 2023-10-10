package BookMap.PentaRim.ControllerDto;


import BookMap.PentaRim.Book.Dto.BookPersonalRequestDto;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class BookSaveDto {


    String isbn;
    BookPersonalRequestDto bookPersonalRequestDto;
    String sessionId;
}
