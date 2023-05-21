package BookMap.PentaRim.Controller.BookMap;

import BookMap.PentaRim.BookMap.dto.TagRequestDto;
import BookMap.PentaRim.service.BookMapTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookMapController {
    final BookMapTestService bookMapTestService;
    @PostMapping("/bookmap/hashtag/save/{id}")
    public void savetags(@PathVariable Long id, @RequestBody TagRequestDto tagRequestDto){
        bookMapTestService.tagssave(id, tagRequestDto);
    }

    @PostMapping("/bookmap/hashtag/update/{id}")
    public void updateTags(@PathVariable Long id, @RequestBody TagRequestDto tagRequestDto){
        bookMapTestService.tagsUpdate(id, tagRequestDto);
    }

    @DeleteMapping("/bookmap/hashtag/deleteall/{id}")
    public void deleteAllTags(@PathVariable Long id){
        bookMapTestService.tagsDelete(id);
    }

    @GetMapping("/bookmap/hashtag")
    public ResponseEntity<?> bookmap(@RequestParam String tag){
        return new ResponseEntity<>(bookMapTestService.findBookMapByTag(tag), HttpStatus.OK);
    }
}
