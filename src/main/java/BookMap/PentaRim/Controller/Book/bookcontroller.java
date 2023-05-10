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
    public void bookMemoSave(@PathVariable Long id, @RequestBody BookMemoRequestDto bookMemoRequestDto){
        bookMemoService.save(id, bookMemoRequestDto);
    }

    @GetMapping("/book/{userid}")
    public ResponseEntity<?> bookPersonaLoad(@PathVariable Long userid){
        return new ResponseEntity<>(bookSaved.findByUser(userid), HttpStatus.OK);
    }

    @PostMapping("/book/changestate/{id}")
    public void changeBookState(@PathVariable Long userId, @RequestParam Long bookId, @RequestBody BookState bookState){

    }

}
