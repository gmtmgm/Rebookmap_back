package BookMap.PentaRim.Book;

import BookMap.PentaRim.Book.Dto.BookPersonalRequestDto;
import BookMap.PentaRim.Repository.BookMemoRepository;
import BookMap.PentaRim.Repository.BookPersonalRepository;
import BookMap.PentaRim.Repository.BookRepository;
import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.User.model.User;
import BookMap.PentaRim.service.BookSearchService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class BookMemoRepositoryTest {

    @Autowired
    BookMemoRepository bookMemoRepository;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookPersonalRepository bookPersonalRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookSearchService bookSearchService;

    @BeforeEach
    public void setup(){
        bookMemoRepository.deleteAll();
        bookPersonalRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    public void 북메모저장(){

        //given: 유저와 책은 저장된 상태
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
        BookPersonal savedbookPersonal = bookPersonalRepository.save(bookPersonal.toEntity());

        //when

        String content ="다시 읽고 싶은 책이다";
        Integer page = 123;

        bookMemoRepository.save(BookMemo.builder()
                        .page(page)
                        .saved(LocalDateTime.now())
                        .content(content)
                        .bookPersonal(savedbookPersonal)
                .build());

        List<BookMemo> bookMemoList = bookMemoRepository.findAll();

        BookMemo bookMemo = bookMemoList.get(0);

        //assertThat(bookMemo.getBookPersonal()).isEqualTo(bookPersonal);
        assertThat(bookMemo.getContent()).isEqualTo(content);
        assertThat(bookMemo.getPage()).isEqualTo(page);
    }

    @Test
    @DisplayName("DB에 잘 저장된지 확인")
    public void findMemo(){
        //given: 유저와 책은 저장된 상태
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
        BookPersonal savedbookPersonal = bookPersonalRepository.save(bookPersonal.toEntity());

        //when
        String content ="다시 읽고 싶은 책이다";
        Integer page = 123;

        BookMemo bookMemo1 = bookMemoRepository.save(BookMemo.builder()
                .page(page)
                .saved(LocalDateTime.now())
                .content(content)
                .bookPersonal(savedbookPersonal)
                .build());

        BookMemo bookMemo2 = bookMemoRepository.save(BookMemo.builder()
                .page(156)
                .saved(LocalDateTime.now())
                .content("재미있다")
                .bookPersonal(savedbookPersonal)
                .build());

        //then
        assertThat(bookMemoRepository.count()).isEqualTo(2);
        assertThat(content).isEqualTo(bookMemo1.getContent());
    }

    @Test
    @DisplayName("북메모 삭제되는지 확인")
    void deleteBookMemo(){
        //given: 유저와 책은 저장된 상태
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
        BookPersonal savedbookPersonal = bookPersonalRepository.save(bookPersonal.toEntity());

        String content ="다시 읽고 싶은 책이다";
        Integer page = 123;

        BookMemo bookMemo1 = bookMemoRepository.save(BookMemo.builder()
                .page(page)
                .saved(LocalDateTime.now())
                .content(content)
                .bookPersonal(savedbookPersonal)
                .build());

        //when
        bookMemoRepository.delete(bookMemo1);

        //then
        assertThat(bookMemoRepository.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("메모 수정되는지 확인")
    void changeBookMemo(){
        //given: 유저와 책은 저장된 상태
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
        BookPersonal savedbookPersonal = bookPersonalRepository.save(bookPersonal.toEntity());

        String content ="다시 읽고 싶은 책이다";
        Integer page = 123;

        BookMemo bookMemo1 = bookMemoRepository.save(BookMemo.builder()
                .page(page)
                .saved(LocalDateTime.now())
                .content(content)
                .bookPersonal(savedbookPersonal)
                .build());

        //when
        String updateContent = "수정된 내용이다";
        Integer updatePage = 456;
        bookMemo1.update(updateContent,LocalDateTime.now(),updatePage);

        //then
        assertThat(updatePage).isEqualTo(bookMemo1.getPage());
        assertThat(updateContent).isEqualTo(bookMemo1.getContent());
        assertThat(bookMemoRepository.count()).isEqualTo(1);
    }
}
