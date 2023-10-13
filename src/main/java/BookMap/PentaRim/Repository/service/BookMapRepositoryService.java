package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.BookMap.BookMapEntity;
import BookMap.PentaRim.BookMap.Dto.*;
import jakarta.transaction.Transactional;

import java.util.List;


public interface BookMapRepositoryService {

    BookMap EntityToBookMap(Long bookMapId);
    BookMapRequestDto ToBookMapRequestDto(BookMap bookMap);

    List<String> findHashTagByBookMap(BookMapEntity bookMapEntity);
    List<BookMapResponseDto> findBookMapsByUserId(Long userId);
    List<BookMapResponseDto> findBookMapScrapsByUserId(Long userId);

    Long findBookMapScrapIdByUserIdAndBookMapId(Long userId, Long bookMapId);

    Long saveBookMap(Long userId, BookMapSaveRequestDto bookMapSaveRequestDto);
    boolean saveBookMapScrap(Long userId, Long bookMapId);
    void saveToMyBookMap(Long userId, Long bookMapId);

    void updateBookMap(BookMapEntity bookMapEntity, BookMapRequestDto bookMapRequestDto);
    BookMap updateBookMapAll(Long bookMapId, BookMap bookMap);

    void bookMapDelete(Long bookMapId);
    void bookMapDetailDelete(Long bookMapId);
    void bookMapScrapDelete(Long bookMapScrapId);

    void tagssave(BookMapEntity bookMapEntity, TagRequestDto tagRequestDto);
    void tagsUpdate(BookMapEntity bookMapEntity, TagRequestDto tagRequestDto);
    void tagsDelete(BookMapEntity bookMapEntity);

    List<BookMapResponseDto> findBookMapByTag(String tag);
    List<BookMapResponseDto> searchBookMap(String text);
}
