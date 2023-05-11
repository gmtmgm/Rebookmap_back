package BookMap.PentaRim.Book;

import BookMap.PentaRim.Repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    public void 책_불러오기(){
        String title = "미움받을 용기";
        String publisher = "인플루엔셜";
        String isbn = "8996991341 9788996991342";  //수정전 isbn
        String image = "https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1467038%3Ftimestamp%3D20230128141840";
        String author = "기시미 이치로, 고가 후미타케";



        //bookRepository.save(new Book(title, author, publisher, isbn, image));

        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() ->
                new IllegalArgumentException("해당 book이 없습니다."));

        assertThat(book.getIsbn()).isEqualTo(isbn);
        assertThat(book.getTitle()).isEqualTo(title);
        assertThat(book.getAuthor()).isEqualTo(author);
        assertThat(book.getImage()).isEqualTo(image);
        assertThat(book.getPublisher()).isEqualTo(publisher);

        //bookRepository.deleteAll();

    }
}
