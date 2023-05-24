package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.*;
import BookMap.PentaRim.BookMap.Dto.*;
import BookMap.PentaRim.Repository.*;
import BookMap.PentaRim.User.User;
import BookMap.PentaRim.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookMapRepositoryServiceImpl implements BookMapRepositoryService {
    final UserRepository userRepository;
    final BookRepository bookRepository;
    final BookMapRepository bookMapRepository;
    final BookMapDetailRepository bookMapDetailRepository;
    final BookListRepository bookListRepository;



    @Override
    @Transactional
    public BookMap EntityToBookMap(Long bookMapId) { //저장소 > BookMap 객체
        BookMap bookMap = new BookMap();
        bookMap.setBookMapId(bookMapId);
        BookMapEntity bookMapEntity = bookMapRepository.findById(bookMapId)
                .orElseThrow(() -> new
                        IllegalArgumentException("북맵 없음" + bookMapId));
        List<BookMapDetailEntity> detailList = bookMapDetailRepository.findByBookMapEntity(bookMapEntity);
//        for(BookMapDetailEntity detail: detailList){
//            if (detail.getType() == "Book") {
//                List<BookListEntity> bookList = bookListRepository.findByBookMapDetailEntity(detail);
//                ArrayList<Book> map = new ArrayList<>();
//                for (BookListEntity b: bookList){
//                    Book book = b.getBook();
//                    map.add(book);
//                }
//                bookMap.addObj(map);
//            } else if (detail.getType() == "Memo") {
//                String memo = detail.getMemo();
//                bookMap.addObj(memo);
//            } else {
//                new IllegalArgumentException("타입 미지정" + detail.getBookMapDetailId());
//            }
//        }
        return bookMap;
    }

    @Override
    @Transactional
    public List<BookMapResponseDto> findByUserId(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + userId));
        List<BookMapEntity> bookMapEntity = bookMapRepository.findByUser(user); //예외처리 안하기!!
        List<BookMapResponseDto> bookMapList = new ArrayList<>();
        for (BookMapEntity bookMap : bookMapEntity){
            bookMapList.add(new BookMapResponseDto(bookMap));
        }
        return bookMapList;
    }

    @Override
    @Transactional
    public List<BookMapDetailResponseDto> findByBookMapId(Long bookMapId){ //예외처리 다 빼기!!
        BookMapEntity bookMapEntity = bookMapRepository.findByBookMapId(bookMapId);
        List<BookMapDetailEntity> bookMapDetailEntiy = bookMapDetailRepository.findByBookMapEntityOrderByIndex(bookMapEntity);
        List<BookMapDetailResponseDto> bookMapDetailList = new ArrayList<>();
        for (BookMapDetailEntity bookMapDetail : bookMapDetailEntiy){
            bookMapDetailList.add(new BookMapDetailResponseDto(bookMapDetail));
        }
        return bookMapDetailList;
    }

    @Override
    @Transactional
    public List<BookListResponseDto> findByBookMapDetailId(Long bookMapDetailId){ //책목록
        BookMapDetailEntity bookMapDetailEntity = bookMapDetailRepository.findByBookMapDetailId(bookMapDetailId);
        List<BookListEntity> bookListEntity = bookListRepository.findByBookMapDetailOrderByIndex(bookMapDetailEntity);
        List<BookListResponseDto> bookList = new ArrayList<>();
        for (BookListEntity book : bookListEntity){
            bookList.add(new BookListResponseDto(book));
        }
        return bookList;
    }

    @Override
    @Transactional
    public Book findByBookListId(Long bookListId){
        BookListEntity bookListEntity = bookListRepository.findByBookListId(bookListId);
        return bookListEntity.getBook();
    }

    @Override
    @Transactional
    public BookMapDetailResponseDto BookMapDetailEntityToDto(Long bookMapDetailId){
        return new BookMapDetailResponseDto(bookMapDetailRepository.findByBookMapDetailId(bookMapDetailId));
    }

    @Override
    @Transactional
    public void updateBookMap(Long bookMapId, BookMapRequestDto bookMapRequestDto){
        BookMapEntity bookMapEntity = bookMapRepository.findById(bookMapId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookMap이 없습니다. " + bookMapId));
        bookMapEntity.update(
                bookMapRequestDto.getBookMapTitle(),
                bookMapRequestDto.getBookMapContent(),
                bookMapRequestDto.getHashTag());
    }

    @Override
    @Transactional
    public void updateBookMapDetail(Long bookMapDetailId, BookMapDetailRequestDto bookMapDetailRequestDto){
        BookMapDetailEntity bookMapDetailEntity = bookMapDetailRepository.findById(bookMapDetailId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookMapDetail이 없습니다. " + bookMapDetailId));
        bookMapDetailEntity.update(
                bookMapDetailRequestDto.getMemo(),
                bookMapDetailRequestDto.getIndex());
    }

    @Override
    @Transactional
    public void updateBookList(Long bookListId, BookListRequestDto bookListRequestDto){
        BookListEntity bookListEntity = bookListRepository.findById(bookListId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookList가 없습니다. " + bookListId));
        bookListEntity.update(
                bookListRequestDto.getBook(),
                bookListRequestDto.getIndex());
    }

    @Override
    @Transactional
    public void bookMapDelete(Long bookMapId){
        BookMapEntity bookMapEntity = bookMapRepository.findById(bookMapId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookMap이 없습니다." + bookMapId));
        bookMapRepository.delete(bookMapEntity);
    }

    @Override
    @Transactional
    public void bookMapDetailDelete(Long bookMapDetailId){
        BookMapDetailEntity bookMapDetailEntity = bookMapDetailRepository.findById(bookMapDetailId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookMapDetail이 없습니다. " + bookMapDetailId));
        bookMapDetailRepository.delete(bookMapDetailEntity);
    }

    @Override
    @Transactional
    public void bookListDelete(Long bookListId){
        BookListEntity bookListEntity = bookListRepository.findById(bookListId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookList가 없습니다." + bookListId));
        bookListRepository.delete(bookListEntity);
    }

}
