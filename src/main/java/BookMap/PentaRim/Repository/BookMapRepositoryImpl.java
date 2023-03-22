package BookMap.PentaRim.Repository;

import BookMap.PentaRim.BookMap.BookMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


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
