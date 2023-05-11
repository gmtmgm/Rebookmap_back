package BookMap.PentaRim.Book;

import BookMap.PentaRim.Repository.BookMemoRepository;
import BookMap.PentaRim.Repository.BookPersonalRepository;
import BookMap.PentaRim.User.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class BookMemoRepositoryTest {

    @Autowired
    BookMemoRepository bookMemoRepository;

    @Autowired
    BookPersonalRepository bookPersonalRepository;

    @Test
    public void 북메모저장_불러오기(){
        BookPersonal bookPersonal = bookPersonalRepository.findById(1L).orElseThrow(
                () -> new IllegalArgumentException(" bookpersonal 못 찾음")
        );


        String content ="다시 읽고 싶은 책이다";
        Integer page = 123;

        bookMemoRepository.save(BookMemo.builder()
                        .page(page)
                        .saved(LocalDateTime.now())
                        .content(content)
                        .bookPersonal(bookPersonal)
                .build());

        List<BookMemo> bookMemoList = bookMemoRepository.findAll();

        BookMemo bookMemo = bookMemoList.get(0);

        //assertThat(bookMemo.getBookPersonal()).isEqualTo(bookPersonal);
        assertThat(bookMemo.getContent()).isEqualTo(content);
        assertThat(bookMemo.getPage()).isEqualTo(page);

        bookMemoRepository.deleteAll();
    }
}
