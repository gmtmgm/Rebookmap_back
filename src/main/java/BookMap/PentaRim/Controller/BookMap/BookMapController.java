package BookMap.PentaRim.Controller.BookMap;

import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.BookMap.Dto.*;
import BookMap.PentaRim.BookMapControllerDto.BookMapSaveDto;
import BookMap.PentaRim.BookMapControllerDto.BookMapScrapSaveDto;
import BookMap.PentaRim.Repository.service.BookMapRepositoryService;
import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.User.model.User;
import BookMap.PentaRim.User.ouath.provider.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.Session;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin("https://8172-203-255-63-30.ngrok-free.app")
public class BookMapController {

    private final BookMapRepositoryService bookMapRepositoryService;

    private final UserRepository userRepository;

    private final JdbcIndexedSessionRepository sessionRepository;


    @PostMapping("/bookmap") //특정 유저의 북맵 목록
    public ResponseEntity<?> userBookMapLoad(@RequestHeader String sessionId){
        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {
            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            return new ResponseEntity<>(bookMapRepositoryService.findBookMapsByUserId(user.getId()),HttpStatus.OK);
        }

        log.info("cannot find session");
        Exception e = new Exception();
        throw new RuntimeException(e);

    }

    @GetMapping("/bookmap/{userID}") //데이터 보내는 용!! 테스트 사용 끝나면 지울 예정
    public ResponseEntity<?> userBookMapLoadTest(@PathVariable Long userID){
        return new ResponseEntity<>(bookMapRepositoryService.findBookMapsByUserId(userID),HttpStatus.OK);
    }

    @PostMapping("/bookmap/scrap") //특정 유저의 스크랩한 북맵 목록
    public ResponseEntity<?> userBookMapScrapLoad(@RequestHeader String sessionId){
        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            return new ResponseEntity<>(bookMapRepositoryService.findBookMapScrapsByUserId(user.getId()),HttpStatus.OK);
        }

        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);
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
    public Long bookMapSave(@RequestHeader String sessionId, @RequestBody BookMapSaveRequestDto bookMapSaveRequestDto){
        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {
            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            return bookMapRepositoryService.saveBookMap(user.getId(), bookMapSaveRequestDto);
        }

        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);
    }

//    @PostMapping("/bookmap/save/{id}") //특정 유저의 북맵 저장
//    public Long bookMapSave1(@PathVariable Long id, @RequestBody BookMapSaveRequestDto bookMapSaveRequestDto){
//        log.info(bookMapSaveRequestDto.getBookMapTitle());
//        log.info(bookMapSaveRequestDto.getBookMapContent());
//        return bookMapRepositoryService.saveBookMap(id, bookMapSaveRequestDto);
//    }



    @PostMapping("/bookmap/scrap/save/{bookMapId}") //특정 유저의 북맵 스크랩 저장
    public boolean userBookMapScrapSave(@RequestHeader String sessionId, @PathVariable Long bookMapId){
        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            return bookMapRepositoryService.saveBookMapScrap(user.getId(), bookMapId);
        }

        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);
    }

//    @PostMapping("/bookmap/scrap/save/{id}/{bookmapid}") //특정 유저의 북맵 스크랩 저장
//    public boolean userBookMapScrapSave1(@PathVariable Long id,@PathVariable Long bookmapid){
//        return bookMapRepositoryService.saveBookMapScrap(id, bookmapid);
//    }


    //이거도 유형이 같아서 북맵스크랩세이브디티오 그대로 사용
    @PostMapping("bookmap/tomy/save/{bookMapId}") //다른 유저의 북맵을 내걸로 저장
    public void bookMapSaveToMy(@RequestBody BookMapScrapSaveDto bookMapScrapSaveDto){
        Session findSession = sessionRepository.findById(bookMapScrapSaveDto.getSessionId());
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            bookMapRepositoryService.saveToMyBookMap(user.getId(), bookMapScrapSaveDto.getBookmapid());
        }

        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);
    }

    @PostMapping("/bookmap/update/{bookMapId}") //특정 북맵의 북맵 수정
    public BookMap bookMapUpdate(@PathVariable Long bookMapId, @RequestBody BookMap bookMap) {
        bookMapRepositoryService.updateBookMapAll(bookMapId, bookMap);
        return bookMap;
    } //내용 확인용으로 리턴값 넣은거라 나중에 고치기

    @PostMapping("/bookmap/get/scrap/{bookMapId}") //특정 스크랩 id
    public ResponseEntity<?> getBookMapScrapId(@PathVariable Long bookMapId, @RequestHeader String sessionId){
        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            return new ResponseEntity<>(bookMapRepositoryService.findBookMapScrapIdByUserIdAndBookMapId(user.getId(), bookMapId),HttpStatus.OK);
        }

        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);
    }



    @DeleteMapping("/bookmap/delete/{bookMapId}") //특정 북맵 삭제
    public void deleteBookMap(@PathVariable Long bookMapId){ bookMapRepositoryService.bookMapDelete(bookMapId); }

    @DeleteMapping("/bookmap/scrap/delete/{bookMapScrapId}") //특정 스크랩 삭제
    public void deleteBookMapScrap(@PathVariable Long bookMapScrapId){ bookMapRepositoryService.bookMapScrapDelete(bookMapScrapId); }


}