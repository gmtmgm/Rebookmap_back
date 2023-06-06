package BookMap.PentaRim.BookMap.Test;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.BookMap.BookMapEntity;
import BookMap.PentaRim.BookMap.Dto.BookMapScrapRequestDto;
import BookMap.PentaRim.Repository.BookMapRepository;
import BookMap.PentaRim.Repository.service.BookMapRepositoryService;
import BookMap.PentaRim.service.BookSaved;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookMapUpdateTest {
    final BookSaved bookSaved;
    final BookMapRepositoryService bookMapRepositoryService;
    final BookMapRepository bookMapRepository;

    public BookMap bookMapTest(Long bookMapId){
        BookMap bookMap = new BookMap();
        bookMap.setBookMapId(bookMapId);
        bookMap.setBookMapTitle("북맵 제목");
        bookMap.setBookMapContent("북맵 설명 어쩌구저쩌구");
        ArrayList<String> hashTag = new ArrayList<>();
        hashTag.add("태그");
        hashTag.add("태그 확인");
        bookMap.setHashTag(hashTag);
        ArrayList<Book> map = new ArrayList<>();
        List<String> isbns = new ArrayList<>();
        isbns.add("8996991341");
        isbns.add("9791190090261");
        for(String isbn : isbns){
            Book book = bookSaved.saveBookToRepo(isbn);
            if (isbns.indexOf(isbn) == 0){
                bookMap.setBookMapImage(book.getImage());
            }
            map.add(book);
        }
//        bookMap.addObj(map, 1L);
        bookMap.addObj("BookMap 메모 테스트", 2L);

        return bookMap;
    }

    public void scrapTest(){
        BookMapEntity bookMap = bookMapRepository.findById(1L).orElseThrow();
        BookMapScrapRequestDto bookMapScrapRequestDto = new BookMapScrapRequestDto();
        bookMapScrapRequestDto.setBookMap(bookMap);
        bookMapRepositoryService.saveBookMapScrap(1L, bookMapScrapRequestDto);
    }
}