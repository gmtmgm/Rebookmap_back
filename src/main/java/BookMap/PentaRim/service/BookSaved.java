package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.Dto.BookPersonalRequestDto;
import BookMap.PentaRim.Book.Dto.BookPersonalResponseDto;
import BookMap.PentaRim.Book.Dto.BookPersonalUpdateRequestDto;
import BookMap.PentaRim.Book.Dto.BookPersonalUpdateStateDto;

import java.util.List;

// 저장소와 연결하고 구현? 아니면 그냥 구현?
public interface BookSaved {

    void Reading(Long id, BookPersonalRequestDto bookPersonalRequestDto);
    void changeState(Long id, String isbn, BookPersonalUpdateStateDto bookPersonalUpdateStateDto);

    void changeAll(Long id, String isbn, BookPersonalUpdateRequestDto bookPersonalUpdateRequestDto);

    void deleteBook(Long id, String isbn);
    List<BookPersonalResponseDto> findByUser(Long id);

}