package BookMap.PentaRim.ControllerDto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class BookDeleteDto {


    String isbn;
    String sessionId;
}
