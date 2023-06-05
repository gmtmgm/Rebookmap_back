package BookMap.PentaRim.Book;

import BookMap.PentaRim.Book.Dto.BookPersonalRequestDto;
import BookMap.PentaRim.Repository.BookPersonalRepository;
import BookMap.PentaRim.Repository.BookRepository;
import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.User.model.User;
import BookMap.PentaRim.service.BookSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;

@SpringBootTest
public class BookPersonalRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookPersonalRepository bookPersonalRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookSearchService bookSearchService;

    @BeforeEach
    public void setup(){
        bookPersonalRepository.deleteAll();
        bookRepository.deleteAll();

    }

    @Test
    @DisplayName("개인 저장된 책이 DB에 나타나는지 확인")
    void saveBookPersonal(){

        //given: 읽는 중인, 유저는 1로 지정
        User user = userRepository.findById(1L).orElseThrow(
                ()-> new IllegalArgumentException("해당 유저가 없습니다."));
        Book book = bookSearchService.searchBooks("9791168473690");
        String bookState = "읽는중인";
        LocalDate startDate = LocalDate.now();
        Integer readingPage = 10;
        Integer totalPage = 300;
        BookPersonalRequestDto bookPersonal = new BookPersonalRequestDto();
        bookPersonal.setUser(user);
        bookPersonal.setBook(book);
        bookPersonal.setBookState(bookState);
        bookPersonal.setStartDate(startDate);
        bookPersonal.setReadingPage(readingPage);
        bookPersonal.setTotalPage(totalPage);

        //when
        bookRepository.save(book);
        BookPersonal savedbookPersonal = bookPersonalRepository.save(bookPersonal.toEntity());

        //then
        assertThat(user.getId()).isEqualTo(savedbookPersonal.getUser().getId());
        assertThat(book.getIsbn()).isEqualTo(savedbookPersonal.getBook().getIsbn());
        assertThat(bookPersonalRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("저장된 책이 잘 조회되는지 확인")
    void findBookPersonal(){
        //given: user는 1, 일단 임의의 책 저장
        User user = userRepository.findById(1L).orElseThrow(
                ()-> new IllegalArgumentException("해당 유저가 없습니다."));
        Book book = bookSearchService.searchBooks("9791168473690");
        String bookState = "읽는중인";
        LocalDate startDate = LocalDate.now();
        Integer readingPage = 10;
        Integer totalPage = 300;
        BookPersonalRequestDto bookPersonal = new BookPersonalRequestDto();
        bookPersonal.setUser(user);
        bookPersonal.setBook(book);
        bookPersonal.setBookState(bookState);
        bookPersonal.setStartDate(startDate);
        bookPersonal.setReadingPage(readingPage);
        bookPersonal.setTotalPage(totalPage);

        Book book2 = bookSearchService.searchBooks("9791167740984");
        String bookState2 = "읽고싶은";
        BookPersonalRequestDto bookPersonal2 = new BookPersonalRequestDto();
        bookPersonal2.setUser(user);
        bookPersonal2.setBook(book2);
        bookPersonal2.setBookState(bookState);

        //when
        bookRepository.save(book);
        bookRepository.save(book2);
        BookPersonal bookPersonal1 = bookPersonalRepository.save(bookPersonal.toEntity());
        BookPersonal bookPersoanl2 = bookPersonalRepository.save(bookPersonal2.toEntity());

        //then
        assertThat(bookPersonalRepository.count()).isEqualTo(2);
        assertThat(user.getId()).isEqualTo(bookPersonal1.getUser().getId());
        assertThat(book.getIsbn()).isEqualTo(bookPersonal1.getBook().getIsbn());
    }

    @Test
    @DisplayName("저장된 책 삭제되는지 확인")
    void deleteBookPersonal(){
        //given: user는 1, 일단 임의의 책 저장
        User user = userRepository.findById(1L).orElseThrow(
                ()-> new IllegalArgumentException("해당 유저가 없습니다."));
        Book book = bookSearchService.searchBooks("9791168473690");
        String bookState = "읽는중인";
        LocalDate startDate = LocalDate.now();
        Integer readingPage = 10;
        Integer totalPage = 300;
        BookPersonalRequestDto bookPersonal = new BookPersonalRequestDto();
        bookPersonal.setUser(user);
        bookPersonal.setBook(book);
        bookPersonal.setBookState(bookState);
        bookPersonal.setStartDate(startDate);
        bookPersonal.setReadingPage(readingPage);
        bookPersonal.setTotalPage(totalPage);

        bookRepository.save(book);
        BookPersonal savedBookPersonal = bookPersonalRepository.save(bookPersonal.toEntity());

        //when
        bookPersonalRepository.delete(savedBookPersonal);

        //then
        assertThat(bookPersonalRepository.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("개인 책 상태 변경")
    void changeBookPersonal(){
        //given user:1, 책 한권 저장
        User user = userRepository.findById(1L).orElseThrow(
                ()-> new IllegalArgumentException("해당 유저가 없습니다."));
        Book book = bookSearchService.searchBooks("9791168473690");
        String bookState = "읽는중인";
        LocalDate startDate = LocalDate.now();
        Integer readingPage = 10;
        Integer totalPage = 300;
        BookPersonalRequestDto bookPersonal = new BookPersonalRequestDto();
        bookPersonal.setUser(user);
        bookPersonal.setBook(book);
        bookPersonal.setBookState(bookState);
        bookPersonal.setStartDate(startDate);
        bookPersonal.setReadingPage(readingPage);
        bookPersonal.setTotalPage(totalPage);

        bookRepository.save(book);
        BookPersonal savedBookPersonal = bookPersonalRepository.save(bookPersonal.toEntity());

        //when
        savedBookPersonal.setBookState(BookState.DONE);
        savedBookPersonal.setEndDate(LocalDate.now());
        savedBookPersonal.setGrade(4.0F);

        //then
        assertThat(bookPersonalRepository.count()).isEqualTo(1);
        assertThat(savedBookPersonal.getBookState()).isEqualTo(BookState.DONE);
        assertThat(savedBookPersonal.getGrade()).isEqualTo(4.0F);
    }
}
