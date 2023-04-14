package BookMap.PentaRim.BookMap;
import BookMap.PentaRim.AppConfig.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BookMapSerialNumTest {

    AnnotationConfigApplicationContext ac = new
            AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("북맵의 시리얼 넘버 증가 확인")
    void create_Map() {

       BookMap bookMap1 = new BookMap();
       BookMap bookMap2 = new BookMap();
       assertThat(bookMap2.getSerialNum()).isEqualTo(2);


    }


}
