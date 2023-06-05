package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.BookMap.BookMapEntity;
import BookMap.PentaRim.BookMap.Dto.*;

import java.util.List;


public interface BookMapRepositoryService {

    BookMap EntityToBookMap(Long bookMapId);
    BookMapRequestDto ToBookMapRequestDto(BookMap bookMap);

    List<String> findHashTagByBookMap(BookMapEntity bookMapEntity);
    List<BookMapResponseDto> findBookMapsByUserId(Long userId);
    List<BookMapResponseDto> findBookMapScrapsByUserId(Long userId);

    void saveBookMap(Long userId, BookMapSaveRequestDto bookMapSaveRequestDto);
    void saveBookMapScrap(Long userId, BookMapScrapRequestDto bookMapScrapRequestDto);

    void updateBookMap(BookMapEntity bookMapEntity, BookMapRequestDto bookMapRequestDto);
    BookMap updateBookMapAll(Long bookMapId, BookMap bookMap);

    void bookMapDelete(Long bookMapId);
    void bookMapDetailDelete(Long bookMapId);

    void tagssave(BookMapEntity bookMapEntity, TagRequestDto tagRequestDto);
    void tagsUpdate(BookMapEntity bookMapEntity, TagRequestDto tagRequestDto);
    void tagsDelete(BookMapEntity bookMapEntity);

    List<BookMapResponseDto> findBookMapByTag(String tag);
    List<BookMapResponseDto> searchBookMap(String text);
}
