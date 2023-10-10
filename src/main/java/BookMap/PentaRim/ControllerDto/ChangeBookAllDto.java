package BookMap.PentaRim.ControllerDto;


import BookMap.PentaRim.Book.Dto.BookPersonalUpdateRequestDto;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class ChangeBookAllDto {


    String isbn;

    BookPersonalUpdateRequestDto bookPersonalUpdateRequestDto;
    String sessionId;
}
