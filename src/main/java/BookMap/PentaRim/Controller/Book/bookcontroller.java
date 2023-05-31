package BookMap.PentaRim.Controller.Book;

import BookMap.PentaRim.Book.Dto.*;
import BookMap.PentaRim.User.CustomUserDetails;
import BookMap.PentaRim.User.model.User;
import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.User.UserRequestDto;
import BookMap.PentaRim.service.BookSaved;
import BookMap.PentaRim.service.BookSearchService;
import BookMap.PentaRim.service.TotalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin("https://a934-203-255-63-30.ngrok-free.app")
@Slf4j
public class bookcontroller {


    /**
     *authentication 테스트했던 것들
     */
    /*
    @GetMapping("/user")
    public String getUser(Authentication authentication) {

        //HttpSession session = request.getSession();
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //String username = (String) httpSession.getAttribute("username");
        //OAuth2User oAuth2User = customOAuth2UserService.loadUser(oAuth2UserRequest);

        return "";
    }

     */



    private final BookSaved bookSaved;

    private final UserRepository userRepository;

    private final TotalService totalService;

    //DB 저장 확인용 controller 작성함

    private final BookSearchService bookSearchService;


    @PostMapping("/test")
    public String test(@RequestParam String text){
        System.out.println(text);
        return text;
    }

    //@PostMapping
    @PostMapping("/usertest")
    public boolean usertest(@RequestBody UserRequestDto userRequestDto){
        System.out.println(userRepository.existsByNickname(userRequestDto.getNickname()));
        return userRepository.existsByNickname(userRequestDto.getNickname());
    }

    @GetMapping(value = "/book/{id}",produces = "application/json; charset=utf8")
    public ResponseEntity<?> bookPersonaLoad(@PathVariable Long id){

        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //String username = userDetails.getUsername();
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

    //여기서부터 페이지별 컨트롤러

    /**
     *
     * @param id
     * @return 메인페이지 요소들
     */
    @GetMapping("/main/{id}")
    public ResponseEntity<?> main_test(@PathVariable Long id){
        return new ResponseEntity<>(totalService.main(id), HttpStatus.OK);
    }
    /*

    @GetMapping("/main")
    public ResponseEntity<?> main(Authentication authentication){
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return new ResponseEntity<>(totalService.main(customUserDetails.getUser().getId()), HttpStatus.OK);
    }

     */

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

    /**
     *
     * @param id
     * @param bookPersonalMonthRequestDto: year, month
     * @return 통계별 완독 책
     */
    @GetMapping("/summary/{id}")
    public ResponseEntity<?> bookPersonalMoth(@PathVariable Long id, @RequestBody BookPersonalMonthRequestDto bookPersonalMonthRequestDto){
        return new ResponseEntity<>(bookSaved.findByMonth(id,bookPersonalMonthRequestDto), HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/profile/{id}")
    public ResponseEntity<?> profile(@PathVariable Long id){
        return new ResponseEntity<>(totalService.profile(id), HttpStatus.OK);
    }

    @GetMapping("/bookdetail/{id}")
    public ResponseEntity<?> bookdetail(@PathVariable Long id, @RequestParam String isbn){
        return new ResponseEntity<>(bookSaved.bookPersonalDetail(id, isbn), HttpStatus.OK);
    }


    @PostMapping("/book/save/{id}")
    public boolean bookSave(@PathVariable Long id, @RequestParam String isbn, @RequestBody BookPersonalRequestDto bookPersonalRequestDto){
        return bookSaved.booksave(id, isbn, bookPersonalRequestDto);
    }


    @GetMapping("/book/detailpage")
    public ResponseEntity<?> detailPage(@RequestParam String isbn){
        return new ResponseEntity<>(bookSearchService.searchBooks(isbn), HttpStatus.OK);
    }


}
