package BookMap.PentaRim.Controller.BookMap;

import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.BookMap.Dto.*;
import BookMap.PentaRim.Repository.service.BookMapRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin("https://8172-203-255-63-30.ngrok-free.app")
public class BookMapController {

    private final BookMapRepositoryService bookMapRepositoryService;


    @GetMapping("/bookmap/{userId}") //특정 유저의 북맵 목록
    public ResponseEntity<?> userBookMapLoad(@PathVariable Long userId){
        return new ResponseEntity<>(bookMapRepositoryService.findBookMapsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/bookmap/scrap/{userId}") //특정 유저의 스크랩한 북맵 목록
    public ResponseEntity<?> userBookMapScrapLoad(@PathVariable Long userId){
        return new ResponseEntity<>(bookMapRepositoryService.findBookMapScrapsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/bookmap/view/{bookMapId}") //특정 북맵의 세부 정보
    public BookMap bookMapLoad(@PathVariable Long bookMapId){
        return bookMapRepositoryService.EntityToBookMap(bookMapId);
    }



    @PostMapping("/bookmap/save/{userId}") //특정 유저의 북맵 저장
    public void bookMapSave(@PathVariable Long userId, @RequestBody BookMapSaveRequestDto bookMapSaveRequestDto){
        bookMapRepositoryService.saveBookMap(userId, bookMapSaveRequestDto);
        //아직 이 시점에서는 요구하는 값 없으므로 void로 두기
    }

    @PostMapping("/bookmap/update/{bookMapId}") //특정 북맵의 북맵 수정
    public BookMap bookMapUpdate(@PathVariable Long bookMapId, @RequestBody BookMap bookMap) {
        bookMapRepositoryService.updateBookMapAll(bookMapId, bookMap);
        return bookMap;
    }

    @PostMapping("/bookmap/scrap/save/{userId}") //특정 유저의 북맵 스크랩 저장
    public void userBookMapScrapSave(@PathVariable Long userId, @RequestBody BookMapScrapRequestDto bookMapScrapRequestDto){
        bookMapRepositoryService.saveBookMapScrap(userId, bookMapScrapRequestDto);
    }

    @DeleteMapping("/bookmap/delete/{bookMapId}") //특정 북맵 삭제
    public void deleteBookMap(@PathVariable Long bookMapId){ bookMapRepositoryService.bookMapDelete(bookMapId); }


    @GetMapping("/bookmap/hashtag")
    public ResponseEntity<?> bookmap(@RequestParam String tag){
        return new ResponseEntity<>(bookMapRepositoryService.findBookMapByTag(tag), HttpStatus.OK);
    }
}