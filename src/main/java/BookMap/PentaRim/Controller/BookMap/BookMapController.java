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

    @GetMapping("/bookmap/search/{text}") //검색 페이지
    public ResponseEntity<?> bookMapSearchLoad(@PathVariable String text){
        return new ResponseEntity<>(bookMapRepositoryService.searchBookMap(text), HttpStatus.OK);
    }



    @PostMapping("/bookmap/save/{userId}") //특정 유저의 북맵 저장
    public Long bookMapSave(@PathVariable Long userId, @RequestBody BookMapSaveRequestDto bookMapSaveRequestDto){
        return bookMapRepositoryService.saveBookMap(userId, bookMapSaveRequestDto);
    }

    @PostMapping("/bookmap/scrap/save/{userId}") //특정 유저의 북맵 스크랩 저장
    public boolean userBookMapScrapSave(@PathVariable Long userId, @RequestBody BookMapScrapRequestDto bookMapScrapRequestDto){
        return bookMapRepositoryService.saveBookMapScrap(userId, bookMapScrapRequestDto);
    }

    @PostMapping("bookmap/tomy/save/{userId}/{bookMapId}") //다른 유저의 북맵을 내걸로 저장
    public void bookMapSaveToMy(@PathVariable Long userId, @PathVariable Long bookMapId){
        bookMapRepositoryService.saveToMyBookMap(userId, bookMapId);
    }

    @PostMapping("/bookmap/update/{bookMapId}") //특정 북맵의 북맵 수정
    public BookMap bookMapUpdate(@PathVariable Long bookMapId, @RequestBody BookMap bookMap) {
        bookMapRepositoryService.updateBookMapAll(bookMapId, bookMap);
        return bookMap;
    } //내용 확인용으로 리턴값 넣은거라 나중에 고치기




    @DeleteMapping("/bookmap/delete/{bookMapId}") //특정 북맵 삭제
    public void deleteBookMap(@PathVariable Long bookMapId){ bookMapRepositoryService.bookMapDelete(bookMapId); }

    @DeleteMapping("/bookmap/scrap/delete/{bookMapScrapId}") //특정 스크랩 삭제
    public void deleteBookMapScrap(@PathVariable Long bookMapScrapId){ bookMapRepositoryService.bookMapScrapDelete(bookMapScrapId); }

//    @GetMapping("/bookmap/hashtag")
//    public ResponseEntity<?> bookmap(@RequestParam String tag){
//        return new ResponseEntity<>(bookMapRepositoryService.findBookMapByTag(tag), HttpStatus.OK);
//    }
}