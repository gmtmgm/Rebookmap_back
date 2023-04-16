package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.Repository.BookMapRepository;
import BookMap.PentaRim.Repository.BookMapRepositoryImpl;

public class BookMapRepositoryServiceImpl {

    private final BookMapRepository bookMapRepository = new
            BookMapRepositoryImpl();
    public void join(BookMap bookMap) {
        bookMapRepository.save(bookMap);
    }
    public BookMap findBookMap(Long bookMapId) {
        return bookMapRepository.findByBookMapId(bookMapId);
    }
}
