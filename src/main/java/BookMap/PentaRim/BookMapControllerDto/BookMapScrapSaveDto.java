package BookMap.PentaRim.BookMapControllerDto;


import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

@Data
public class BookMapScrapSaveDto {

    Long bookmapid;
    String sessionId;
}
