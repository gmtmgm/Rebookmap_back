package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.BookState;
import BookMap.PentaRim.Book.Dto.BookMemoResponseDto;
import BookMap.PentaRim.Book.Dto.BookTopResponseDto;
import BookMap.PentaRim.Book.Dto.ProfileMemoResponseDto;
import BookMap.PentaRim.Dto.BookShelfResponseDto;
import BookMap.PentaRim.Dto.MainResponseDto;
import BookMap.PentaRim.Dto.ProfileResponseDto;
import BookMap.PentaRim.Dto.ProfileUpdateRequestDto;

import java.util.List;

public interface TotalService {
    MainResponseDto main(Long id);
    List<BookShelfResponseDto> bookshelf(Long id);
    List<BookTopResponseDto> mostBooks();
    List<BookShelfResponseDto> readBooks(Long id);
    List<BookShelfResponseDto> readingBooks(Long id);
    List<BookShelfResponseDto> bookshelfState(Long id, BookState bookState);
    List<BookShelfResponseDto> wantBooks(Long id);
    ProfileResponseDto profile(Long id);
    void profileUpdate(Long id, ProfileUpdateRequestDto profileUpdateRequestDto);
    List<ProfileMemoResponseDto> findMemoByUser(Long id);
}
