package BookMap.PentaRim;

import BookMap.PentaRim.Book.SearchBook;
import BookMap.PentaRim.Book.SearchListBooks;
import BookMap.PentaRim.service.BookSearchService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.assertj.core.api.Assertions.*;
import java.text.SimpleDateFormat;

@SpringBootTest
public class SearchBookTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    BookSearchService bookSearchService = ac.getBean(BookSearchService.class);
    @Test
    @DisplayName("카카오 책 api 정보 가져오와서 book객체에 넣기")
    void searchBook() {
        SearchListBooks listBooks = bookSearchService.searchBooks("8996991341");
        //검색할때는 isbn 10자리 13자리 합쳐서는 검색이 안됨 한가지만 검색해야함
        SearchBook book = listBooks.getBooks().get(0);   //listbooks은 책 검색한 후 나오는 책 리스트 전체를 받음,
        assertThat(book.getIsbn()).isEqualTo("8996991341 9788996991342");

        //출판 날짜는 date타입이므로 사용할때 string으로 사용하려면 다음과 같이 할 것!
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        String date = simpleDateFormat.format(book.getPublishedDay());
        System.out.println(date);
        //@tostring 어노테이션을 통한 searchbook 객체 출력
        System.out.println(book);
    }

}
