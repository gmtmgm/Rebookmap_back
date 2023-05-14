package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.*;
import BookMap.PentaRim.Book.Dto.*;
import BookMap.PentaRim.Repository.BookMemoRepository;
import BookMap.PentaRim.Repository.BookPersonalRepository;
import BookMap.PentaRim.Repository.BookRepository;
import BookMap.PentaRim.User.User;
import BookMap.PentaRim.User.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookSavedImpl implements BookSaved{

    final UserRepository userRepository;
    final BookRepository bookRepository;
    final BookPersonalRepository bookPersonalRepository;
    final BookMemoRepository bookMemoRepository;

    private final EntityManager em;

    @Override
    @Transactional
    public void Reading(Long id, BookPersonalRequestDto bookPersonalRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

        if(bookRepository.existsByIsbn(bookPersonalRequestDto.getBook().getIsbn()) == true){  //책 존재할 경우 그냥 넘어감

        }else{
            bookRepository.save(bookPersonalRequestDto.getBook());  //존재하지 않을경우 book DB에 저장
            bookPersonalRequestDto.setUser(user);
            bookPersonalRepository.save(bookPersonalRequestDto.toEntity());
        }

    }

    @Override
    @Transactional
    public List<BookPersonalResponseDto> findByUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        List<BookPersonal> list = bookPersonalRepository.findByUser(user);
        List<BookPersonalResponseDto> bookPersonalResponseDtoList = new ArrayList<>();
        for(BookPersonal bookPersonal: list){
            bookPersonalResponseDtoList.add(new BookPersonalResponseDto(bookPersonal));
        }
        return bookPersonalResponseDtoList;
    }

    @Override
    @Transactional
    public void changeState(Long id, String isbn, BookPersonalUpdateStateDto bookPersonalUpdateStateDto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() ->  new
                        IllegalArgumentException(("해당 책이 없습니다.")));

        BookPersonal bookPersonal = bookPersonalRepository.findByUserAndBook(user, book)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookpersonal이 없습니다."));

        bookPersonal.updateState(bookPersonalUpdateStateDto.getBookState());
    }

    @Override
    @Transactional
    public void changeAll(Long id, String isbn, BookPersonalUpdateRequestDto bookPersonalUpdateRequestDto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() ->  new
                        IllegalArgumentException(("해당 책이 없습니다.")));

        BookPersonal bookPersonal = bookPersonalRepository.findByUserAndBook(user, book)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookpersonal이 없습니다."));

        bookPersonal.update(bookPersonalUpdateRequestDto.getBookState(), bookPersonalUpdateRequestDto.startDate,
                bookPersonalUpdateRequestDto.endDate, bookPersonalUpdateRequestDto.readingPage,
                bookPersonalUpdateRequestDto.totalPage, bookPersonalUpdateRequestDto.grade);
    }

    @Override
    @Transactional
    public void deleteBook(Long id, String isbn){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() ->  new
                        IllegalArgumentException(("해당 책이 없습니다.")));

        BookPersonal bookPersonal = bookPersonalRepository.findByUserAndBook(user, book)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookpersonal이 없습니다."));

        bookPersonalRepository.delete(bookPersonal);
    }

    @Override
    @Transactional
    public void bookMemoSave(Long id, String isbn, BookMemoRequestDto bookMemoRequestDto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() ->  new
                        IllegalArgumentException(("해당 책이 없습니다.")));

        BookPersonal bookPersonal = bookPersonalRepository.findByUserAndBook(user, book)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookpersonal이 없습니다."));

        BookMemo bookMemo = BookMemo.builder()
                .bookPersonal(bookPersonal)
                .saved(LocalDateTime.now())
                .page(bookMemoRequestDto.getPage())
                .content(bookMemoRequestDto.getContent())
                .build();

        bookMemoRepository.save(bookMemo);
    }

    @Override
    @Transactional
    public void bookMemoUpdate(Long id, BookMemoRequestDto bookMemoRequestDto){
        //현재는 진짜 bookMemo id로만 수정가능하게 둠
        BookMemo bookMemo = bookMemoRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookMemo가 없습니다."));

        bookMemo.update(bookMemoRequestDto.getContent(),
                LocalDateTime.now(),
                bookMemoRequestDto.getPage());
    }

    @Override
    @Transactional
    public void bookMemoDelete(Long id){
        BookMemo bookMemo = bookMemoRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookMemo가 없습니다."));
        bookMemoRepository.delete(bookMemo);
    }

    @Override
    @Transactional
    public List<BookMemoResponseDto> findByUserAndBook(Long id, String isbn){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() ->  new
                        IllegalArgumentException(("해당 책이 없습니다.")));

        BookPersonal bookPersonal = bookPersonalRepository.findByUserAndBook(user, book)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookpersonal이 없습니다."));

        List<BookMemo> bookMemoList = bookMemoRepository.findByBookPersonalOrderBySavedDesc(bookPersonal);
        List<BookMemoResponseDto> bookMemoResponseDtoList = new ArrayList<>();
        for(BookMemo bookMemo: bookMemoList){
            bookMemoResponseDtoList.add(new BookMemoResponseDto(bookMemo));
        }
        return bookMemoResponseDtoList;
    }
}
