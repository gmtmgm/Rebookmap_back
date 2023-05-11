package BookMap.PentaRim.Controller.Book;

import BookMap.PentaRim.Book.*;
import BookMap.PentaRim.Repository.BookPersonalRepository;
import BookMap.PentaRim.User.User;
import BookMap.PentaRim.User.UserRepository;
import BookMap.PentaRim.service.BookMemoService;
import BookMap.PentaRim.service.BookSaved;
import BookMap.PentaRim.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("https://8172-203-255-63-30.ngrok-free.app")
public class bookcontroller {
    private final BookSearchService bookSearchService;
    private final BookMemoService bookMemoService;
    private final BookSaved bookSaved;

    private final BookPersonalRepository bookPersonalRepository;


    private final UserRepository userRepository;

    @PostMapping("/book/save/{id}")
    public void bookSave(@PathVariable Long id, @RequestParam String isbn, @RequestBody BookPersonalRequestDto bookPersonalRequestDto){
        //Book requestbook = bookPersonalRequestDto.getBook();
        Book book = bookSearchService.searchBooks(isbn);
        bookPersonalRequestDto.setBook(book);
        bookSaved.Reading(id, bookPersonalRequestDto);
    }
    //지금 파라미터를 뭘로 해야할지 안정했음

    @PostMapping("/bookmemo/save/{id}")
    public void bookMemoSave(@PathVariable Long id, @PathVariable String isbn, @RequestBody BookMemoRequestDto bookMemoRequestDto){
        bookMemoService.save(id, isbn, bookMemoRequestDto);
    }

    @GetMapping(value = "/book/{userid}",produces = "application/json; charset=utf8")
    public ResponseEntity<?> bookPersonaLoad(@PathVariable Long userid){
        return new ResponseEntity<>(bookSaved.findByUser(userid), HttpStatus.OK);
    }

    @PostMapping("/book/changeall/{id}")
    public void changeBookAll(@PathVariable Long userId, @RequestParam Long bookId, @RequestBody BookState bookState){

    }
    @DeleteMapping("/book/delete/{id}")
    public void deletebook(@PathVariable Long userId, @RequestParam String isbn){
        bookSaved.deleteBookPersonal(userId, isbn);
    }

    @DeleteMapping("/book/memo/{id}")
    public void deletememo(@PathVariable Long userId, @RequestParam Long memoId){

    }

}
