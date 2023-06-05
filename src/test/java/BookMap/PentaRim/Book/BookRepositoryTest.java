package BookMap.PentaRim.Book;

import BookMap.PentaRim.Repository.BookPersonalRepository;
import BookMap.PentaRim.Repository.BookRepository;
import BookMap.PentaRim.service.BookSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookPersonalRepository bookPersonalRepository;
    @Autowired
    BookSearchService bookSearchService;

    @BeforeEach
    public void setup() {
        // 데이터 초기화 작업 수행
        bookRepository.deleteAll();
        bookPersonalRepository.deleteAll();
        // 다른 초기화 작업 수행
    }

    @Test
    @DisplayName("책이 DB에 저장이 잘 되는지 확인")
    void saveBook(){
        //given
        Book exampleBook = bookSearchService.searchBooks("8996991341");
        //when
        Book savedBook = bookRepository.save(exampleBook);
        //then
        assertThat(exampleBook.getIsbn()).isEqualTo(savedBook.getIsbn());
        assertThat(exampleBook.getAuthor()).isEqualTo(savedBook.getAuthor());
        assertThat(exampleBook.getImage()).isEqualTo(savedBook.getImage());
        assertThat(savedBook.getId()).isNotNull();
        assertThat(bookRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("저장된 책이 제대로 조회되는지 확인")
    void findBook(){
        //given
        Book savedBook = bookRepository.save(bookSearchService.searchBooks("8996991341"));
        Book savedBook2 = bookRepository.save(bookSearchService.searchBooks("9791168473690"));
        //when
        Book findBook = bookRepository.findByIsbn(savedBook.getIsbn())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 isbn: " + savedBook.getId()));
        Book findBook2 = bookRepository.findByIsbn(savedBook2.getIsbn())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 isbn: " + savedBook2.getId()));

        //then
        assertThat(bookRepository.count()).isEqualTo(2);
        assertThat(findBook.getTitle()).isEqualTo("미움받을 용기");
        assertThat(findBook.getIsbn()).isEqualTo("8996991341");
        assertThat(findBook.getPublisher()).isEqualTo("인플루엔셜");
        assertThat(findBook.getAuthor()).isEqualTo("기시미 이치로, 고가 후미타케");

    }


}
