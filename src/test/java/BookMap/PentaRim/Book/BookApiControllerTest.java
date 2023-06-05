package BookMap.PentaRim.Book;

import BookMap.PentaRim.Repository.BookPersonalRepository;
import BookMap.PentaRim.Repository.BookRepository;
import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.service.BookSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookPersonalRepository bookPersonalRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookSearchService bookSearchService;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup(){
        bookPersonalRepository.deleteAll();
        bookRepository.deleteAll();
    }


    @Test
    public void 책_등록된다() throws Exception {
        /*

        //Book book = bookSearchService.searchBooks("9791168473690");
        String state = "읽고싶은";
        LocalDate startDate = LocalDate.now();
        Integer readingPage = 100;
        Integer totalPage = 450;
        Long id = 1L;

        //Optional<User> user = userRepository.findById(id);

        BookPersonalRequestDto bookPersonalRequestDto = BookPersonalRequestDto.builder()
                .bookState(state)
                .readingPage(readingPage)
                .startDate(startDate)
                .totalPage(totalPage)
                .build();

        String url = "http://localhost:" + port + "/book/save/" + id + " ?isbn=9791168473690";

        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url, bookPersonalRequestDto, Boolean.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        //ssertThat(responseEntity.getBody()).isGreaterThan(0L);

        // user = userRepository.findById(id).orElseThrow(() ->
          //      new IllegalArgumentException("해당 user 발견 못함"));

        //Book savedbook = bookRepository.findByIsbn("9791188331888").orElseThrow(() ->
          //      new IllegalArgumentException("해당 book을 발견 못함"));

        List<BookPersonal> bookPersonalList = bookPersonalRepository.findAll();
        assertThat(bookPersonalList.get(0).getBook().getTitle()).isEqualTo("미움받을 용기");

         */

    }
}
