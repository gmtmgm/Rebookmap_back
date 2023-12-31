package BookMap.PentaRim.Book;

import BookMap.PentaRim.service.BookSearchService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class SearchBookTest {
    @Autowired
    BookSearchService bookSearchService;
    @Test
    @DisplayName("카카오 책 api 정보 가져오와서 book객체에 넣기")
    void searchBook() {
        //주석은 필요한 것외에는 나중에 지울 예정

        //검색할때는 isbn 10자리 13자리 합쳐서는 검색이 안됨, 한가지 입력해야합니다.
        Book book = bookSearchService.searchBooks("8996991341");   //listbooks은 책 검색한 후 나오는 책 리스트에서 제일 첫번째걸로 받습니다.
        //isbn으로 검색하면 한 개만 나옵니다.
        assertThat(book.getIsbn()).isEqualTo("8996991341"); //저장은 10과 13이 string으로 "156151 156154"으로 저장됩니다.


        //프린트 실행: Book(title=미움받을 용기, author=[기시미 이치로, 고가 후미타케], publisher=인플루엔셜, hashTag=null, publishedDay=Mon Nov 17 00:00:00 KST 2014,
        // isbn=8996991341 9788996991342, image=https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1467038%3Ftimestamp%3D20230128141840)

        //참고
        //출판 날짜는 date타입이므로 사용할때 string으로 사용하고싶다면 다음과 같이 할 것!
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        //String date = simpleDateFormat.format(book.getPublishedDay());
        //@tostring 어노테이션을 통한 searchbook 객체 출력
    }

}
