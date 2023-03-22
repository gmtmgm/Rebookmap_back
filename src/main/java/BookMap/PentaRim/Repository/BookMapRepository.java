package BookMap.PentaRim.Repository;


import BookMap.PentaRim.BookMap.BookMap;
import org.springframework.stereotype.Component;

@Component
public interface BookMapRepository {

    void save(BookMap bookMap);

    BookMap findByBookMapId(Long bookMapId);
}
