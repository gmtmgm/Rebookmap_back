package BookMap.PentaRim.Controller.BookMap;

import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.BookMap.Dto.*;
import BookMap.PentaRim.Repository.service.BookMapRepositoryService;
import BookMap.PentaRim.User.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin("https://8172-203-255-63-30.ngrok-free.app")
public class BookMapController {

    private final BookMapRepositoryService bookMapRepositoryService;


    @GetMapping("/bookmap") //특정 유저의 북맵 목록
    public ResponseEntity<?> userBookMapLoad(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return new ResponseEntity<>(bookMapRepositoryService.findBookMapsByUserId(customUserDetails.getUser().getId()), HttpStatus.OK);
    }

    @GetMapping("/bookmap/scrap") //특정 유저의 스크랩한 북맵 목록
    public ResponseEntity<?> userBookMapScrapLoad(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return new ResponseEntity<>(bookMapRepositoryService.findBookMapScrapsByUserId(customUserDetails.getUser().getId()), HttpStatus.OK);
    }

    @GetMapping("/bookmap/view/{bookMapId}") //특정 북맵의 세부 정보
    public BookMap bookMapLoad(@PathVariable Long bookMapId){
        return bookMapRepositoryService.EntityToBookMap(bookMapId);
    }

    @GetMapping(value = "/bookmap/search/{text}", produces = "application/json; charset=utf8") //검색 페이지
    public ResponseEntity<?> bookMapSearchLoad(@PathVariable String text){
        return new ResponseEntity<>(bookMapRepositoryService.searchBookMap(text), HttpStatus.OK);
    }



    @PostMapping("/bookmap/save") //특정 유저의 북맵 저장
    public Long bookMapSave(@RequestBody BookMapSaveRequestDto bookMapSaveRequestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        log.info(bookMapSaveRequestDto.getBookMapTitle());
        log.info(bookMapSaveRequestDto.getBookMapContent());
        return bookMapRepositoryService.saveBookMap(customUserDetails.getUser().getId(), bookMapSaveRequestDto);
    }

    @PostMapping("/bookmap/scrap/save/{bookmapid}") //특정 유저의 북맵 스크랩 저장
    public boolean userBookMapScrapSave(@PathVariable Long bookmapid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return bookMapRepositoryService.saveBookMapScrap(customUserDetails.getUser().getId(), bookmapid);
    }

    @PostMapping("/bookmap/scrap/save/{id}/{bookmapid}") //특정 유저의 북맵 스크랩 저장
    public boolean userBookMapScrapSave1(@PathVariable Long id,@PathVariable Long bookmapid){
        return bookMapRepositoryService.saveBookMapScrap(id, bookmapid);
    }

    @PostMapping("bookmap/tomy/save/{bookMapId}") //다른 유저의 북맵을 내걸로 저장
    public void bookMapSaveToMy(@PathVariable Long bookMapId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        bookMapRepositoryService.saveToMyBookMap(customUserDetails.getUser().getId(), bookMapId);
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