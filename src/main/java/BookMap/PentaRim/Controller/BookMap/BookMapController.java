package BookMap.PentaRim.Controller.BookMap;

import BookMap.PentaRim.BookMap.Dto.*;
import BookMap.PentaRim.Repository.BookListRepository;
import BookMap.PentaRim.Repository.BookMapDetailRepository;
import BookMap.PentaRim.Repository.BookMapRepository;
import BookMap.PentaRim.Repository.BookRepository;
import BookMap.PentaRim.Repository.service.BookMapRepositoryService;
import BookMap.PentaRim.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin("https://8172-203-255-63-30.ngrok-free.app")
public class BookMapController {

    private final BookMapRepository bookMapRepository;
    private final BookMapDetailRepository bookMapDetailRepository;
    private final BookListRepository bookListRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BookMapRepositoryService bookMapRepositoryService;


    @GetMapping("/{userId}/bookMap")
    public ResponseEntity<?> userBookMapLoad(@PathVariable Long userId){
        return new ResponseEntity<>(bookMapRepositoryService.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/bookMap/{bookMapId}")
    public ResponseEntity<?> BookMapLoad(@PathVariable Long userId, @PathVariable Long bookMapId){
        return new ResponseEntity<>(bookMapRepositoryService.findByBookMapId(bookMapId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/bookMap/{bookMapId}/{bookMapDetailId}")
    public ResponseEntity<?> BookMapDetailLoad(@PathVariable Long userId, @PathVariable Long bookMapId, @PathVariable Long bookMapDetailId){
        return new ResponseEntity<>(bookMapRepositoryService.findByBookMapDetailId(bookMapDetailId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/bookMap/{bookMapId}/{bookMapDetailId}/{bookListId}/")
    public ResponseEntity<?> BookListLoad(@PathVariable Long userId, @PathVariable Long bookMapId, @PathVariable Long bookMapDetailId, @PathVariable Long bookListId){
        String type = bookMapRepositoryService.BookMapDetailEntityToDto(bookMapDetailId).getType();
        return new ResponseEntity<>(bookMapRepositoryService.findByBookListId(bookListId), HttpStatus.OK);
    }


    @PostMapping("/bookMap/save/{bookMapId}")
    public void bookMapSave(@PathVariable Long bookMapId, @RequestBody BookMapRequestDto bookMapRequestDto){
        if(bookMapRepository.existsByBookMapId(bookMapId)){
            //BookMap bookMap = bookMapRepositoryService.EntityToBookMap(bookMapId);
        }
        else{
            bookMapRepository.save(bookMapRequestDto.toEntity());
        }
    }
    //@PostMapping("/bookMap/save/{bookMapId}/{}")

    @PostMapping("/bookmap/hashtag/save/{id}")
    public void savetags(@PathVariable Long id, @RequestBody TagRequestDto tagRequestDto){
        bookMapRepositoryService.tagssave(id, tagRequestDto);
    }

    @PostMapping("/bookmap/hashtag/update/{id}")
    public void updateTags(@PathVariable Long id, @RequestBody TagRequestDto tagRequestDto){
        bookMapRepositoryService.tagsUpdate(id, tagRequestDto);
    }

    @DeleteMapping("/bookmap/hashtag/deleteall/{id}")
    public void deleteAllTags(@PathVariable Long id){
        bookMapRepositoryService.tagsDelete(id);
    }

    @GetMapping("/bookmap/hashtag")
    public ResponseEntity<?> bookmap(@RequestParam String tag){
        return new ResponseEntity<>(bookMapRepositoryService.findBookMapByTag(tag), HttpStatus.OK);
    }
}