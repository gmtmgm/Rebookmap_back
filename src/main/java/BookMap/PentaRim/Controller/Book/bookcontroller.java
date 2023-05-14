package BookMap.PentaRim.Controller.Book;

import BookMap.PentaRim.Book.*;
import BookMap.PentaRim.Book.Dto.BookPersonalRequestDto;
import BookMap.PentaRim.Book.Dto.BookPersonalUpdateRequestDto;
import BookMap.PentaRim.Book.Dto.BookPersonalUpdateStateDto;
import BookMap.PentaRim.Repository.BookPersonalRepository;
import BookMap.PentaRim.User.UserRepository;
import BookMap.PentaRim.service.BookSaved;
import BookMap.PentaRim.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("https://8172-203-255-63-30.ngrok-free.app")
public class bookcontroller {

    private final BookSearchService bookSearchService;
    //private final BookMemoService bookMemoService;
    private final BookSaved bookSaved;

    private final BookPersonalRepository bookPersonalRepository;


    private final UserRepository userRepository;

    @PostMapping("/book/save/{id}")
    public Long bookSave(@PathVariable Long id, @RequestParam String isbn, @RequestBody BookPersonalRequestDto bookPersonalRequestDto){
        //Book requestbook = bookPersonalRequestDto.getBook();
        Book book = bookSearchService.searchBooks(isbn);
        bookPersonalRequestDto.setBook(book);
        bookSaved.Reading(id, bookPersonalRequestDto);
        return id;
    }

    @GetMapping(value = "/book/{userid}",produces = "application/json; charset=utf8")
    public ResponseEntity<?> bookPersonaLoad(@PathVariable Long userid){
        return new ResponseEntity<>(bookSaved.findByUser(userid), HttpStatus.OK);
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

    /*
    @PostMapping("/bookmemo/save/{id}")
    public void bookMemoSave(@PathVariable Long id, @PathVariable String isbn, @RequestBody BookMemoRequestDto bookMemoRequestDto){
        bookMemoService.save(id, isbn, bookMemoRequestDto);
    }
    */


    @DeleteMapping("/book/memo/{id}")
    public void deletememo(@PathVariable Long userId, @RequestParam Long memoId){

    }



}
