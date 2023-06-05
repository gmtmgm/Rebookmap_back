package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.BookMap.BookMapEntity;
import BookMap.PentaRim.BookMap.Dto.*;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;


public interface BookMapRepositoryService {

    BookMap EntityToBookMap(Long bookMapId);
    BookMapRequestDto ToBookMapRequestDto(BookMap bookMap);

    ArrayList<BookMapDetailRequestDto> ToBookMapDetail(BookMap bookMap);

    ArrayList<ArrayList<BookListRequestDto>> ToBookList(BookMap bookMap);

    //    LinkedHashMap<Long, BookMapDetailRequestDto> ToBookMapDetail(BookMap bookMap);
//    LinkedHashMap<Long, BookListRequestDto> ToBookList(BookMap bookMap);
    List<String> findHashTagByBookMap(BookMapEntity bookMapEntity);
    List<BookMapResponseDto> findBookMapsByUserId(Long userId);
//    List<BookMapDetailResponseDto> findByBookMapId(Long bookMapId);
//    List<BookListResponseDto> findByBookMapDetailId(Long bookMapDetailId);
//    BookListResponseDto findByBookListId(Long bookListId);
//    Book findByBookListId(Long bookListId);
//    BookMapDetailResponseDto BookMapDetailEntityToDto(Long bookMapDetailId);
    void saveBookMap(Long userId, BookMapSaveRequestDto bookMapSaveRequestDto);
    void updateBookMap(BookMapEntity bookMapEntity, BookMapRequestDto bookMapRequestDto);
//    void updateBookMapDetail(BookMapEntity bookMapEntity, BookMapDetailRequestDto bookMapDetailRequestDto);

    @Transactional
    BookMap updateBookMapAll(Long bookMapId, BookMap bookMap);

    //    void updateBookList(Long bookListId, BookListRequestDto bookListRequestDto);
//    BookMap updateBookMapAll(Long bookMapId, BookMapRequestDto bookMapRequestDto,
//                             /*List<Long> bookMapDetailIds, */ArrayList<BookMapDetailRequestDto> bookMapDetailRequestDtos,
//                             /*List<Long> bookListIds, */ArrayList<ArrayList<BookListRequestDto>> bookListRequestDtos);
    void bookMapDelete(Long bookMapId);
    void bookMapDetailDelete(Long bookMapId);
//    void bookListDelete(Long bookListId);
    void tagssave(BookMapEntity bookMapEntity, TagRequestDto tagRequestDto);
    void tagsUpdate(BookMapEntity bookMapEntity, TagRequestDto tagRequestDto);
    void tagsDelete(BookMapEntity bookMapEntity);
    List<BookMapResponseDto> findBookMapByTag(String tag);
    List<BookMapResponseDto> searchBookMap(String text);

}
