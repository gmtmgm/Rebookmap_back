package BookMap.PentaRim;


import BookMap.PentaRim.service.BookMapService;
import BookMap.PentaRim.service.BookMapServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class AppConfig {

    public BookMapService bookMapService() {
        return new BookMapServiceImpl();
    }
}
