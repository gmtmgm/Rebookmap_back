package BookMap.PentaRim.BookMap.Test;

import BookMap.PentaRim.BookMap.BookMap;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("https://8172-203-255-63-30.ngrok-free.app")
public class TestController {
    private final BookMapUpdateTest bookMapUpdateTest;

    @GetMapping("/bookmap/test/{bookMapId}")
    public BookMap bookMapLoad(@PathVariable Long bookMapId){
        return bookMapUpdateTest.bookMapTest(bookMapId);
    }

    @PostMapping("bookmap/test/scrapsave")
    public void bookMapScrap(){ bookMapUpdateTest.scrapTest(); }
}