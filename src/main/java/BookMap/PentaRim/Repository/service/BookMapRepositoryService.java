package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.BookMap.Dto.*;

import java.util.List;


public interface BookMapRepositoryService {

    BookMap EntityToBookMap(Long bookMapId);

    List<BookMapResponseDto> findByUserId(Long userId);
    List<BookMapDetailResponseDto> findByBookMapId(Long bookMapId);
    List<BookListResponseDto> findByBookMapDetailId(Long bookMapDetailId);
    //BookListResponseDto findByBookListId(Long bookListId);
    Book findByBookListId(Long bookListId);
    BookMapDetailResponseDto BookMapDetailEntityToDto(Long bookMapDetailId);
    void updateBookMap(Long bookMapId, BookMapRequestDto bookMapRequestDto);
    void updateBookMapDetail(Long bookMapDetailId, BookMapDetailRequestDto bookMapDetailRequestDto);
    void updateBookList(Long bookListId, BookListRequestDto bookListRequestDto);
    void bookMapDelete(Long bookMapId);
    void bookMapDetailDelete(Long bookMapDetailId);
    void bookListDelete(Long bookListId);
}
