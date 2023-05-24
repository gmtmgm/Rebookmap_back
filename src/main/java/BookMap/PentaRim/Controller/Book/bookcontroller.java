package BookMap.PentaRim.Controller.Book;

import BookMap.PentaRim.Book.Dto.*;
import BookMap.PentaRim.CustomOAuth2UserService;
import BookMap.PentaRim.Repository.BookPersonalRepository;
import BookMap.PentaRim.User.User;
import BookMap.PentaRim.User.UserRepository;
import BookMap.PentaRim.service.BookSaved;
import BookMap.PentaRim.service.BookSearchService;
import BookMap.PentaRim.service.TotalService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin("https://8172-203-255-63-30.ngrok-free.app")
@Slf4j
public class bookcontroller {



    @GetMapping("/user")
    public String getUser(Authentication authentication) {

        //HttpSession session = request.getSession();
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //String username = (String) httpSession.getAttribute("username");
        //OAuth2User oAuth2User = customOAuth2UserService.loadUser(oAuth2UserRequest);

        return "";
    }


    @GetMapping("/test")
    public String index(@AuthenticationPrincipal Jwt jwt){
        return String.format("Hello, %s!", jwt.getSubject());
    }
    /*

    @GetMapping("/api/profiles")
    public User getUsers(@CookieValue(value = "AUTH", required = true) Cookie authCookie, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();

        hasUser(authCookie);

        Member member = (Member) session.getAttribute("member");

        return member;
    }

     */
    @GetMapping("/index")
    public String index(
            @SessionAttribute(
                    value = "Session Key",
                    required = false
            ) Object sessionValue
    ) {
        log.info("sessionValue: {}", sessionValue);

        return "ok";
    }
    private final BookSearchService bookSearchService;
    private final BookSaved bookSaved;

    private final BookPersonalRepository bookPersonalRepository;



    private final CustomOAuth2UserService customOAuth2UserService;

    private final UserRepository userRepository;

    private final TotalService totalService;

    //DB 저장 확인용 controller 작성함


    @PostMapping("/book/save/{id}")
    public boolean bookSave(@PathVariable Long id, @RequestParam String isbn, @RequestBody BookPersonalRequestDto bookPersonalRequestDto){
        return bookSaved.Reading(id, isbn, bookPersonalRequestDto);
    }

    @GetMapping(value = "/book",produces = "application/json; charset=utf8")
    public ResponseEntity<?> bookPersonaLoad(Authentication authentication){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );

        return new ResponseEntity<>(bookSaved.findByUser(user.getId()), HttpStatus.OK);
    }
    @PostMapping("/book/changestate/{id}")
    public void changeState(@PathVariable Long id, @RequestParam String isbn, @RequestBody BookPersonalUpdateStateDto bookPersonalUpdateStateDto){
        bookSaved.changeState(id, isbn ,bookPersonalUpdateStateDto);
    }

    @PostMapping("/book/changeall/{id}")
    public void changeBookAll(@PathVariable Long id, @RequestParam String isbn
            , @RequestBody BookPersonalUpdateRequestDto bookPersonalUpdateRequestDto){
        bookSaved.changeAll(id, isbn, bookPersonalUpdateRequestDto);
    }

    @DeleteMapping("/book/delete/{id}")
    public void deleteBook(@PathVariable Long id, @RequestParam String isbn){
        bookSaved.deleteBook(id, isbn);
    }


    @PostMapping("/bookmemo/save/{id}")
    public void bookMemoSave(@PathVariable Long id, @RequestParam String isbn, @RequestBody BookMemoRequestDto bookMemoRequestDto){
        bookSaved.bookMemoSave(id, isbn, bookMemoRequestDto);
    }

    @PostMapping("/bookmemo/update/{bookMemoId}")
    public void bookMemoUpdate(@PathVariable Long bookMemoId, @RequestBody BookMemoRequestDto bookMemoRequestDto){
        bookSaved.bookMemoUpdate(bookMemoId, bookMemoRequestDto);
    }

    @DeleteMapping("/book/memo/{bookMemoId}")
    public void deletememo(@PathVariable Long bookMemoId){
        bookSaved.bookMemoDelete(bookMemoId);
    }

    @GetMapping("/bookmemo/{id}")
    public ResponseEntity<?> bookMemoLoad(@PathVariable Long id, @RequestParam String isbn){
        return new ResponseEntity<>(bookSaved.findByUserAndBook(id, isbn), HttpStatus.OK);
    }



    @GetMapping("/book/top2")
    public ResponseEntity<?> findByTop2(){
        return new ResponseEntity<>(bookSaved.findByTop2(),HttpStatus.OK);
    }

    @GetMapping("/book/top10")
    public ResponseEntity<?> findByTop10(){
        return new ResponseEntity<>(bookSaved.findByTop10(),HttpStatus.OK);
    }


    @GetMapping("/main/{id}")
    public ResponseEntity<?> main(@PathVariable Long id){
        return new ResponseEntity<>(totalService.main(id), HttpStatus.OK);
    }

    @GetMapping("/mostbooks")
    public ResponseEntity<?> mostBooks(){
        return new ResponseEntity<>(totalService.mostBooks(), HttpStatus.OK);
    }


    @GetMapping("/bookshelf/allbooks/{id}")
    public ResponseEntity<?> bookshelf(@PathVariable Long id){
        return new ResponseEntity<>(totalService.bookshelf(id), HttpStatus.OK);
    }

    @GetMapping("/bookshelf/readbooks/{id}")
    public ResponseEntity<?> readBooks(@PathVariable Long id){
        return new ResponseEntity<>(totalService.readBooks(id), HttpStatus.OK);
    }
    @GetMapping("/bookshelf/readingbooks/{id}")
    public ResponseEntity<?> readingBooks(@PathVariable Long id){
        return new ResponseEntity<>(totalService.readingBooks(id), HttpStatus.OK);
    }
    @GetMapping("/bookshelf/wantbooks/{id}")
    public ResponseEntity<?> wantBooks(@PathVariable Long id){
        return new ResponseEntity<>(totalService.wantBooks(id), HttpStatus.OK);
    }

    @GetMapping("/summary/{id}")
    public ResponseEntity<?> bookPersonalMoth(@PathVariable Long id, @RequestBody BookPersonalMonthRequestDto bookPersonalMonthRequestDto){
        return new ResponseEntity<>(bookSaved.findByMonth(id,bookPersonalMonthRequestDto), HttpStatus.OK);
    }

}
