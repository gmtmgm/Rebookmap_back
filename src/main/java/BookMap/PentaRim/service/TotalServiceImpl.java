package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Book.BookMemo;
import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Book.BookState;
import BookMap.PentaRim.Book.Dto.*;
import BookMap.PentaRim.BookMap.BookMapEntity;
import BookMap.PentaRim.BookMap.Dto.BookMapResponseDto;
import BookMap.PentaRim.Dto.BookShelfResponseDto;
import BookMap.PentaRim.Dto.MainResponseDto;
import BookMap.PentaRim.Dto.ProfileResponseDto;
import BookMap.PentaRim.Dto.ProfileUpdateRequestDto;
import BookMap.PentaRim.Repository.BookMapRepository;
import BookMap.PentaRim.Repository.BookMemoRepository;
import BookMap.PentaRim.Repository.BookPersonalRepository;
import BookMap.PentaRim.User.Dto.UserSearchResponseDto;
import BookMap.PentaRim.User.model.User;
import BookMap.PentaRim.User.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TotalServiceImpl implements TotalService{
    final UserRepository userRepository;
    final BookPersonalRepository bookPersonalRepository;
    final BookMemoRepository bookMemoRepository;
    final BookSaved bookSaved;
    final BookMapRepository bookMapRepository;

    @Override
    @Transactional
    public MainResponseDto main(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        List<BookPersonal> bookPersonalList = bookPersonalRepository.findTop4ByUserOrderBySavedDesc(user);
        List<Book> bookList = new ArrayList<>();
        for(BookPersonal bookPersonal: bookPersonalList){
            bookList.add(bookPersonal.getBook());
        }
        List<BookImageDto> bookImageDtos = new ArrayList<>();
        for(Book book: bookList){
            bookImageDtos.add(new BookImageDto(book));
        }
        List<BookMapEntity> bookMap = bookMapRepository.findTop3ByUserOrderByBookMapSaveTime(user);
        List<BookMapResponseDto> bookMapResponseDtos = new ArrayList<>();
        for(BookMapEntity bookMapEntity: bookMap) {
            bookMapResponseDtos.add(new BookMapResponseDto(bookMapEntity));
        }
        List<BookTopResponseDto> bookTopResponseDtos = bookSaved.findByTop5();
        return new MainResponseDto(bookImageDtos, bookMapResponseDtos, bookTopResponseDtos);
    }

    @Override
    @Transactional
    public List<BookShelfResponseDto> bookshelf(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        List<BookPersonal> bookPersonalList = bookPersonalRepository.findBookPersonalsByUserOrderBySavedDesc(user);
        List<BookShelfResponseDto> bookShelfResponseDtos = new ArrayList<>();
        for(BookPersonal bookPersonal: bookPersonalList){
            if(bookPersonal.getBookState() == BookState.DONE){
                bookShelfResponseDtos.add(new BookShelfDoneDto(bookPersonal));
            }else if(bookPersonal.getBookState() == BookState.READING){
                bookShelfResponseDtos.add(new BookShelfReadingDto(bookPersonal));
            }else{
                bookShelfResponseDtos.add(new BookShelfWishDto(bookPersonal));
            }
        }
        return bookShelfResponseDtos;
    }

    @Override
    @Transactional
    public List<BookTopResponseDto> mostBooks(){
        return bookSaved.findByTop10();
    }

    @Override
    @Transactional
    public List<BookShelfResponseDto> readBooks(Long id){
        return bookshelfState(id, BookState.DONE);
    }

    @Override
    @Transactional
    public List<BookShelfResponseDto> readingBooks(Long id){
        return bookshelfState(id, BookState.READING);
    }

    @Override
    @Transactional
    public List<BookShelfResponseDto> wantBooks(Long id){
        return bookshelfState(id, BookState.WISH);
    }

    @Override
    @Transactional
    public List<BookShelfResponseDto> bookshelfState(Long id, BookState bookState){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        List<BookPersonal> bookPersonalList = bookPersonalRepository.findBookPersonalsByUserAndBookStateOrderBySaved(user, bookState);
        List<BookShelfResponseDto> bookShelfResponseDtos = new ArrayList<>();
        if (bookState == BookState.DONE){
            for(BookPersonal bookPersonal: bookPersonalList){
                bookShelfResponseDtos.add(new BookShelfDoneDto(bookPersonal));
            }
        } else if (bookState == BookState.READING) {
            for(BookPersonal bookPersonal: bookPersonalList){
                bookShelfResponseDtos.add(new BookShelfReadingDto(bookPersonal));
            }
        }else{
            for(BookPersonal bookPersonal: bookPersonalList){
                bookShelfResponseDtos.add(new BookShelfWishDto(bookPersonal));
            }
        }
        return bookShelfResponseDtos;
    }

    @Override
    @Transactional
    public ProfileResponseDto profile(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        String status;
        if(user.getBook_state() == null){
            status = "";
        }else{
            status = user.getStatus();
        }
        Integer count = bookSaved.findByMonthCount(id);
        List<BookMemo> bookMemoList = bookMemoRepository.findTop2ByBookPersonal_UserOrderBySavedDesc(user);
        List<ProfileMemoResponseDto> profileMemoResponseDtoList = new ArrayList<>();
        for(BookMemo bookMemo: bookMemoList){
            profileMemoResponseDtoList.add(new ProfileMemoResponseDto(bookMemo));
        }
        Integer bookmapCount = bookMapRepository.countBookMapByUser(user);
        return new ProfileResponseDto(user, count, bookmapCount, status,  profileMemoResponseDtoList);
    }

    @Override
    @Transactional
    public void profileUpdate(Long id, ProfileUpdateRequestDto profileUpdateRequestDto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        user.setNickname(profileUpdateRequestDto.getNickName());
        user.setStatus(profileUpdateRequestDto.getStatus());
    }

    @Override
    @Transactional
    public List<ProfileMemoResponseDto> findMemoByUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        List<BookPersonal> bookPersonalList = bookPersonalRepository.findByUser(user);
        List<BookMemo> bookMemoList = bookMemoRepository.findByBookPersonal_UserOrderBySavedDesc(user);
        List<ProfileMemoResponseDto> bookMemoResponseDtoList = new ArrayList<>();
        for(BookMemo bookMemo: bookMemoList){
            bookMemoResponseDtoList.add(new ProfileMemoResponseDto(bookMemo));
        }
        return bookMemoResponseDtoList;
    }

    @Override
    @Transactional
    public List<UserSearchResponseDto> getSearchUsers(Long id, String keyword){
        List<User> users = userRepository.findByNicknameContaining(keyword);

        List<UserSearchResponseDto> userSearchResponseDtos = new ArrayList<>();
        for(User user : users){
                userSearchResponseDtos.add(new UserSearchResponseDto(
                        user.getId(),
                        user.getNickname(),
                        user.getStatus(),
                        false
                ));
        }
        return userSearchResponseDtos;
    }
}
