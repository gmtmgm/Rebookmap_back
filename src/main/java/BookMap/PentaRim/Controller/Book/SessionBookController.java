package BookMap.PentaRim.Controller.Book;


import BookMap.PentaRim.Book.Dto.BookMemoRequestDto;
import BookMap.PentaRim.Book.Dto.BookPersonalRequestDto;
import BookMap.PentaRim.Book.Dto.BookPersonalUpdateRequestDto;
import BookMap.PentaRim.Book.Dto.BookPersonalUpdateStateDto;
import BookMap.PentaRim.ControllerDto.*;
import BookMap.PentaRim.Dto.ProfileUpdateRequestDto;
import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.User.model.User;
import BookMap.PentaRim.User.ouath.provider.SessionConst;
import BookMap.PentaRim.service.BookSaved;
import BookMap.PentaRim.service.TotalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.Session;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SessionBookController {

    private final BookSaved bookSaved;
    private final UserRepository userRepository;
    private final TotalService totalService;


    private final JdbcIndexedSessionRepository sessionRepository;

    //최종 컨트롤러

    //책 저장 기능


    @PostMapping("/book/save")  //책 저장
    public boolean bookSave(@RequestParam String isbn, @RequestHeader String sessionId, @RequestBody BookPersonalRequestDto bookPersonalRequestDto){

        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            return bookSaved.booksave(user.getId(),isbn,bookPersonalRequestDto);
        }

        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);
    }


    @PostMapping("/book/changeall")  //책 정보 변경
    public void changeBookAll(@RequestParam String isbn, @RequestHeader String sessionId, @RequestBody BookPersonalUpdateRequestDto bookPersonalUpdateRequestDto){

        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            bookSaved.changeAll(user.getId(), isbn, bookPersonalUpdateRequestDto);
        }
        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);

    }


    @DeleteMapping("/book/delete") //책 삭제
    public void deleteBook(@RequestParam String isbn, @RequestHeader String sessionId){
        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            bookSaved.deleteBook(user.getId(),isbn);
        }
//        log.info("유효하지 않은 세션입니다");
//        Exception e = new Exception();
//        throw new RuntimeException(e);
    }

    @GetMapping("/book/detail")  //상세 조회
    public ResponseEntity<?> bookdetail(@RequestParam String isbn, @RequestHeader String sessionId){
        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            return new ResponseEntity<>(bookSaved.bookPersonalDetail(user.getId(),isbn),HttpStatus.OK);
        }
        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);

    }

    //책 메모 기능


    @PostMapping("/bookmemo/save")  //책 메모 저장
    public void bookMemoSave1(@RequestParam String isbn, @RequestHeader String sessionId, @RequestBody BookMemoRequestDto bookMemoRequestDto){
        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            bookSaved.bookMemoSave(user.getId(), isbn, bookMemoRequestDto);

        }
        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);

    }


    @PostMapping("/bookmemo/update/{bookMemoId}")  //메모 수정
    public void bookMemoUpdate(@PathVariable Long bookMemoId, @RequestBody BookMemoRequestDto bookMemoRequestDto){
        bookSaved.bookMemoUpdate(bookMemoId, bookMemoRequestDto);
    }

    /**
     *
     * @param bookMemoId
     */
    @DeleteMapping("/bookmemo/delete/{bookMemoId}")  //메모 삭제
    public void deletememo(@PathVariable Long bookMemoId){
        bookSaved.bookMemoDelete(bookMemoId);
    }

    //서재

    /**
     *
     * @return 저장된 모든 책
     */
    @GetMapping("/bookshelf/allbooks")
    public ResponseEntity<?> bookshelf(@RequestHeader String sessionId){
        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
           return new ResponseEntity<>(totalService.bookshelf(user.getId()),HttpStatus.OK);

        }
        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);
    }

    /**
     *
     * @return 읽은 책들
     */

    @GetMapping("/bookshelf/readbooks")
    public ResponseEntity<?> readBooks(@RequestBody String sessionId){
        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            return new ResponseEntity<>(totalService.readBooks(user.getId()),HttpStatus.OK);

        }
        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);
    }

    /**
     *
     * @return 읽는 중인 책들
     */
    @GetMapping("/bookshelf/readingbooks")
    public ResponseEntity<?> readingBooks(@RequestBody String sessionId){

        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            return new ResponseEntity<>(totalService.readingBooks(user.getId()),HttpStatus.OK);

        }
        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);
    }

    /**
     *
     * @return 읽고싶은 책들
     */
    @PostMapping("/bookshelf/wantbooks")
    public ResponseEntity<?> wantBooks(@RequestBody String sessionId){
        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            return new ResponseEntity<>(totalService.wantBooks(user.getId()),HttpStatus.OK);

        }
        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);
    }


    //인기 도서 조회 기능
    @GetMapping("/book/top10")
    public ResponseEntity<?> findByTop10(){
        return new ResponseEntity<>(bookSaved.findByTop10(),HttpStatus.OK);
    }

    @GetMapping("/bookmemo/all")
    public ResponseEntity<?> bookMemoAll(@RequestHeader String sessionId){
        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            return new ResponseEntity<>(totalService.findMemoByUser(user.getId()),HttpStatus.OK);

        }
        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);
    }


    //월별 완독 책



    @PostMapping("/summary")
    public ResponseEntity<?> bookPersonalMonth(@RequestBody SummaryDto summaryDto){

        Session findSession = sessionRepository.findById(summaryDto.getSessionId());
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
           return new ResponseEntity<>(bookSaved.findByYear(user.getId(), summaryDto.getYear()),HttpStatus.OK);

        }
        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);

    }

    /**
     *
     * @return 메인페이지 요소들
     */
    @PostMapping("/main")
    public ResponseEntity<?> main(@RequestBody Map<String, String> sessionId){
        log.info("sessionId : " + sessionId);
        Session findSession = sessionRepository.findById(sessionId.get("sessionId"));
        log.info(String.valueOf(findSession));
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
           return new ResponseEntity<>(totalService.main(user.getId()),HttpStatus.OK);

        }
        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);
    }

    //프로필
    @GetMapping("/profile")
    public ResponseEntity<?> profile(@RequestHeader String sessionId){
        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            return new ResponseEntity<>(totalService.profile(user.getId()),HttpStatus.OK);

        }
        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);
    }

    @PostMapping("/profile/update")
    public void profileUpdate(@RequestHeader String sessionId, @RequestBody ProfileUpdateRequestDto profileUpdateRequestDto){
        log.info("profileUpdate's sessionId : " + sessionId);
        Session findSession = sessionRepository.findById(sessionId);

        log.info("findSession" + findSession);

        log.info(findSession.getAttribute(SessionConst.EMAIL));
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            totalService.profileUpdate(user.getId(), profileUpdateRequestDto);

        }
        //log.info("유효하지 않은 세션입니다");
        //Exception e = new Exception();
        //throw new RuntimeException(e);
    }




    //형식이 똑같아서 북 딜리트디티오 사용
    @PostMapping("/book/savedornot")
    public ResponseEntity<?> savedOrNot(@RequestBody BookDeleteDto bookDeleteDto){

        Session findSession = sessionRepository.findById(bookDeleteDto.getSessionId());
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
           return new ResponseEntity<>(bookSaved.checkSavedOrNot(user.getId(), bookDeleteDto.getIsbn()), HttpStatus.OK);

        }
        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);
    }

    @GetMapping("/search/users")
    public ResponseEntity<?> searchUsers(@RequestParam String keyword, @RequestHeader String sessionId){
        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            return new ResponseEntity<>(totalService.getSearchUsers(user, keyword), HttpStatus.OK);

        }
        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);


    }

    @GetMapping("/search/users/{id}")
    public ResponseEntity<?> searchUsers1(@PathVariable Long id, @RequestParam String keyword){

        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );
        return new ResponseEntity<>(totalService.getSearchUsers(user, keyword), HttpStatus.OK);


    }
    //연습용

    @GetMapping(value = "/book/{id}",produces = "application/json; charset=utf8")
    public ResponseEntity<?> bookPersonaLoad(@PathVariable Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );
        return new ResponseEntity<>(bookSaved.findByUser(user.getId()), HttpStatus.OK);
    }
    @PostMapping("/book/changestate/{id}")
    public void changeState(@PathVariable Long id, @RequestParam String isbn, @RequestBody BookPersonalUpdateStateDto bookPersonalUpdateStateDto){
        bookSaved.changeState(id, isbn ,bookPersonalUpdateStateDto);
    }

    @PostMapping("/book/changeall/{id}")
    public void changeBookAll1(@PathVariable Long id, @RequestParam String isbn
            , @RequestBody BookPersonalUpdateRequestDto bookPersonalUpdateRequestDto){
        bookSaved.changeAll(id, isbn, bookPersonalUpdateRequestDto);
    }

    @DeleteMapping("/book/delete/{id}")
    public void deleteBook1(@PathVariable Long id, @RequestParam String isbn){
        bookSaved.deleteBook(id, isbn);
    }


    @PostMapping("/bookmemo/save/{id}")
    public void bookMemoSave1(@PathVariable Long id, @RequestParam String isbn, @RequestBody BookMemoRequestDto bookMemoRequestDto){
        bookSaved.bookMemoSave(id, isbn, bookMemoRequestDto);
    }


    @GetMapping("/bookmemo/{id}")
    public ResponseEntity<?> bookMemoLoad(@PathVariable Long id, @RequestParam String isbn){
        return new ResponseEntity<>(bookSaved.findByUserAndBook(id, isbn), HttpStatus.OK);
    }

    @GetMapping("/bookmemo/all/{id}")
    public ResponseEntity<?> bookMemoAll1(@PathVariable Long id){
        return new ResponseEntity<>(totalService.findMemoByUser(id), HttpStatus.OK);
    }



    /**
     *
     * @param id
     * @return 메인페이지 요소들
     */

    @GetMapping("/main/{id}")
    public ResponseEntity<?> main_test(@PathVariable Long id){
        return new ResponseEntity<>(totalService.main(id), HttpStatus.OK);
    }
    @GetMapping("/mostbooks")
    public ResponseEntity<?> mostBooks(){
        return new ResponseEntity<>(totalService.mostBooks(), HttpStatus.OK);
    }


    @GetMapping("/bookshelf/allbooks/{id}")
    public ResponseEntity<?> bookshelf1(@PathVariable Long id){
        return new ResponseEntity<>(totalService.bookshelf(id), HttpStatus.OK);
    }



    @GetMapping("/bookshelf/readbooks/{id}")
    public ResponseEntity<?> readBooks1(@PathVariable Long id){
        return new ResponseEntity<>(totalService.readBooks(id), HttpStatus.OK);
    }
    @GetMapping("/bookshelf/readingbooks/{id}")
    public ResponseEntity<?> readingBooks1(@PathVariable Long id){
        return new ResponseEntity<>(totalService.readingBooks(id), HttpStatus.OK);
    }
    @GetMapping("/bookshelf/wantbooks/{id}")
    public ResponseEntity<?> wantBooks1(@PathVariable Long id){
        return new ResponseEntity<>(totalService.wantBooks(id), HttpStatus.OK);
    }
    @GetMapping("/profile/{id}")
    public ResponseEntity<?> profile1(@PathVariable Long id){
        return new ResponseEntity<>(totalService.profile(id), HttpStatus.OK);
    }

    @GetMapping("/bookdetail/{id}")
    public ResponseEntity<?> bookdetail1(@PathVariable Long id, @RequestParam String isbn){
        return new ResponseEntity<>(bookSaved.bookPersonalDetail(id, isbn), HttpStatus.OK);
    }


    @PostMapping("/book/save/{id}")
    public boolean bookSave1(@PathVariable Long id, @RequestParam String isbn, @RequestBody BookPersonalRequestDto bookPersonalRequestDto){
        return bookSaved.booksave(id, isbn, bookPersonalRequestDto);
    }


    @GetMapping("/book/top2")
    public ResponseEntity<?> findByTop2(){
        return new ResponseEntity<>(bookSaved.findByTop2(),HttpStatus.OK);
    }

    @PostMapping("/profile/update/{id}")
    public void profileUpdate1(@PathVariable Long id, @RequestBody ProfileUpdateRequestDto profileUpdateRequestDto){
        totalService.profileUpdate(id, profileUpdateRequestDto);
    }

    @GetMapping("/book/savedornot/{id}")
    public ResponseEntity<?> savedOrNot1(@PathVariable Long id, @RequestParam String isbn){
        return new ResponseEntity<>(bookSaved.checkSavedOrNot(id, isbn), HttpStatus.OK);
    }
}
