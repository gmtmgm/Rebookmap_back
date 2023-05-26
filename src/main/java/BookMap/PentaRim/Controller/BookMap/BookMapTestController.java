package BookMap.PentaRim.Controller.BookMap;

import BookMap.PentaRim.service.BookMapTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookMapTestController {
    final BookMapTestService bookMapTestService;

}
