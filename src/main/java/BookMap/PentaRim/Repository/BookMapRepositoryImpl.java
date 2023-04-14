package BookMap.PentaRim.Repository;

import BookMap.PentaRim.BookMap.BookMap;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BookMapRepositoryImpl implements BookMapRepository{

    private static Map<Long, BookMap> store = new ConcurrentHashMap<>();

    @Override
    public void save(BookMap bookMap) {
        store.put(bookMap.getBookMapId(), bookMap);
    }

    @Override
    public BookMap findByBookMapId(Long bookMapId) {
        return store.get(bookMapId);
    }

}
