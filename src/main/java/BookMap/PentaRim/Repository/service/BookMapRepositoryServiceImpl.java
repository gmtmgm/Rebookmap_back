package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.Repository.BookMapRepository;
import BookMap.PentaRim.Repository.BookMapRepositoryImpl;
import org.springframework.stereotype.Service;


@Service
public class BookMapRepositoryServiceImpl {

    private final BookMapRepository bookMapRepository;

    public BookMapRepositoryServiceImpl(BookMapRepository bookMapRepository){
        this.bookMapRepository = bookMapRepository;
    }

    public void join(BookMap bookMap) {
        bookMapRepository.save(bookMap);
    }
    public BookMap findBookMap(Long bookMapId) {
        return bookMapRepository.findByBookMapId(bookMapId);
    }
}
