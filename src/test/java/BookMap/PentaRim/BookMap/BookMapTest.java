package BookMap.PentaRim.BookMap;
import BookMap.PentaRim.AppConfig;
import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Book.BookState;
import BookMap.PentaRim.service.BookMapService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.assertj.core.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

public class BookMapTest {

    AnnotationConfigApplicationContext ac = new
            AnnotationConfigApplicationContext(AppConfig.class);

    BookMapService bookMapService;

    @Test
    @DisplayName("북맵에 메모와 북맵이 잘 만들어지는지 테스트")
    void createMap() {

        List<String> author = new ArrayList<>();
        author.add("오주연");
        Book book = new Book();
        book.setAuthor(author);
        String publisher = "민음사";
        List<String> hashTag = new ArrayList<>();
        hashTag.add("재미있는");
        String publishedDay = "2020-3-17";
        Long isbn = 12345677L;
        String image = "image";
        BookState bookState = BookState.WISH;

        book.setIsbn(isbn);
        book.setPublishedDay(publishedDay);
        book.setHashTag(hashTag);
        book.setBookstate(bookState);

        BookMap bookMap1 = bookMapService.createBookMap(book);

        assertThat(bookMap1.getBookMapId()).isEqualTo(1);

    }
}
