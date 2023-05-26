package BookMap.PentaRim.Book;

import BookMap.PentaRim.Repository.BookPersonalRepository;
import BookMap.PentaRim.Repository.BookRepository;
import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.service.BookSearchService;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

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


    @After("")
    void delete() {
        bookPersonalRepository.deleteAll();
        bookRepository.deleteAll();
    }


    @Test
    public void 책_등록된다() throws Exception {
        /*
        Book book = bookSearchService.searchBooks("9791168473690");
        String state = "읽고싶은";
        LocalDate startDate = LocalDate.now();
        Integer readingPage = 100;
        Integer totalPage = 450;
        Long id = 1L;

        //Optional<User> user = userRepository.findById(id);

        BookPersonalRequestDto bookPersonalRequestDto = BookPersonalRequestDto.builder()
                .book(book)
                .bookState(BookState.from(state))
                .readingPage(readingPage)
                .startDate(startDate)
                .totalPage(totalPage)
                .build();

        String url = "http://localhost:" + port + "/book/save/1?isbn=9791168473690";

        //ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, bookPersonalRequestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(responseEntity.getBody()).isGreaterThan(0L);

        // user = userRepository.findById(id).orElseThrow(() ->
          //      new IllegalArgumentException("해당 user 발견 못함"));

        //Book savedbook = bookRepository.findByIsbn("9791188331888").orElseThrow(() ->
          //      new IllegalArgumentException("해당 book을 발견 못함"));

        List<BookPersonal> bookPersonalList= bookPersonalRepository.findAll();
        BookPersonal bookPersonal = bookPersonalList.get(0);
        assertThat(bookPersonal.getBook()).isEqualTo(book);

        bookPersonalRepository.deleteAll();
        bookRepository.deleteAll();

    }

         */
    }
}
