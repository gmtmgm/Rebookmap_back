package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.BookMap.Dto.*;

import java.util.LinkedHashMap;
import java.util.List;


public interface BookMapRepositoryService {

    BookMap EntityToBookMap(Long bookMapId);
    BookMapRequestDto ToBookMapRequestDto(BookMap bookMap);
    LinkedHashMap<Long, BookMapDetailRequestDto> ToBookMapDetail(BookMap bookMap);
    LinkedHashMap<Long, BookListRequestDto> ToBookList(BookMap bookMap);
    List<BookMapResponseDto> findByUserId(Long userId);
    List<BookMapDetailResponseDto> findByBookMapId(Long bookMapId);
    List<BookListResponseDto> findByBookMapDetailId(Long bookMapDetailId);
    //BookListResponseDto findByBookListId(Long bookListId);
    Book findByBookListId(Long bookListId);
    BookMapDetailResponseDto BookMapDetailEntityToDto(Long bookMapDetailId);
    void saveBookMap(Long userId, BookMapSaveRequestDto bookMapSaveRequestDto);
    void updateBookMap(Long bookMapId, BookMapRequestDto bookMapRequestDto);
    void updateBookMapDetail(Long bookMapDetailId, BookMapDetailRequestDto bookMapDetailRequestDto);
    void updateBookList(Long bookListId, BookListRequestDto bookListRequestDto);
    void bookMapDelete(Long bookMapId);
    void bookMapDetailDelete(Long bookMapDetailId);
    void bookListDelete(Long bookListId);
    void tagssave(Long id, TagRequestDto tagRequestDto);
    void tagsUpdate(Long id, TagRequestDto tagRequestDto);
    void tagsDelete(Long id);
    List<BookMapResponseDto> findBookMapByTag(String tag);
    BookMap updateBookMapAll(Long bookMapId, BookMapRequestDto bookMapRequestDto,
                          List<Long> bookMapDetailIds, List<BookMapDetailRequestDto> bookMapDetailRequestDtos,
                          List<Long> bookListIds, List<BookListRequestDto> bookListRequestDtos);
}
