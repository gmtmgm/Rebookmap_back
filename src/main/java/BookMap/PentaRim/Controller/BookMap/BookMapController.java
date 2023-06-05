package BookMap.PentaRim.Controller.BookMap;

import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.BookMap.Dto.*;
import BookMap.PentaRim.Repository.service.BookMapRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequiredArgsConstructor
@CrossOrigin("https://8172-203-255-63-30.ngrok-free.app")
public class BookMapController {

    private final BookMapRepositoryService bookMapRepositoryService;


    @GetMapping("/bookmap/{userId}")
    public ResponseEntity<?> userBookMapLoad(@PathVariable Long userId){
        return new ResponseEntity<>(bookMapRepositoryService.findBookMapsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/bookmap/view/{bookMapId}")
    public BookMap bookMapLoad(@PathVariable Long bookMapId){
        return bookMapRepositoryService.EntityToBookMap(bookMapId);
    }


//    @GetMapping("/bookmap/{userId}/{bookMapId}/{bookMapDetailId}")
//    public ResponseEntity<?> BookMapDetailLoad(@PathVariable Long userId, @PathVariable Long bookMapId, @PathVariable Long bookMapDetailId){
//        return new ResponseEntity<>(bookMapRepositoryService.findByBookMapDetailId(bookMapDetailId), HttpStatus.OK);
//    }
//
//    @GetMapping("/bookmap/{userId}/{bookMapId}/{bookMapDetailId}/{bookListId}/")
//    public ResponseEntity<?> BookListLoad(@PathVariable Long userId, @PathVariable Long bookMapId, @PathVariable Long bookMapDetailId, @PathVariable Long bookListId){
//        String type = bookMapRepositoryService.BookMapDetailEntityToDto(bookMapDetailId).getType();
//        return new ResponseEntity<>(bookMapRepositoryService.findByBookListId(bookListId), HttpStatus.OK);
//    }



    @PostMapping("/bookmap/save/{userId}")
    public void bookMapSave(@PathVariable Long userId, @RequestBody BookMapSaveRequestDto bookMapSaveRequestDto){
        bookMapRepositoryService.saveBookMap(userId, bookMapSaveRequestDto);
        //아직 이 시점에서는 요구하는 값 없으므로 void로 두기
    }

    @PostMapping("/bookmap/update/{bookMapId}")
    public BookMap bookMapUpdate(@PathVariable Long bookMapId, @RequestBody BookMap bookMap){
        BookMap newBookMap = bookMapRepositoryService.updateBookMapAll(bookMapId,
                bookMapRepositoryService.ToBookMapRequestDto(bookMap),
                new ArrayList<>(bookMapRepositoryService.ToBookMapDetail(bookMap).keySet()),
                new ArrayList<>(bookMapRepositoryService.ToBookMapDetail(bookMap).values()),
                new ArrayList<>(bookMapRepositoryService.ToBookList(bookMap).keySet()),
                new ArrayList<>(bookMapRepositoryService.ToBookList(bookMap).values())
        );
        return newBookMap;
    }



    @DeleteMapping("/bookmap/delete/{bookMapId}")
    public void deleteBookMap(@PathVariable Long bookMapId){ bookMapRepositoryService.bookMapDelete(bookMapId); }




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

    //해쉬태그를 따로 로딩하는 형식이 아니여서 북맵 로딩에 합쳐야 할듯

    @GetMapping("/bookmap/hashtag")
    public ResponseEntity<?> bookmap(@RequestParam String tag){
        return new ResponseEntity<>(bookMapRepositoryService.findBookMapByTag(tag), HttpStatus.OK);
    }
}