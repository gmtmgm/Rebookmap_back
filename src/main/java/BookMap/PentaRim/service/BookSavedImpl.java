package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.*;
import BookMap.PentaRim.Book.Dto.*;
import BookMap.PentaRim.Dto.BookPersonalStateResponseDto;
import BookMap.PentaRim.Dto.BookPersonalDoneStateResponseDto;
import BookMap.PentaRim.Dto.BookPersonalReadingStateResponseDto;
import BookMap.PentaRim.Dto.BookPersonalWishStateResponseDto;
import BookMap.PentaRim.Repository.*;
import BookMap.PentaRim.User.model.User;
import BookMap.PentaRim.User.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookSavedImpl implements BookSaved{

    final UserRepository userRepository;
    final BookRepository bookRepository;
    final BookPersonalRepository bookPersonalRepository;
    final BookMemoRepository bookMemoRepository;
    final BookSearchService bookSearchService;

    @Override
    @Transactional
    public boolean booksave(Long id, String isbn, BookPersonalRequestDto bookPersonalRequestDto) {
//        Book book = bookSearchService.searchBooks(isbn);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

//        //OrElse로 변경 가능함!
//        if(bookRepository.existsByIsbn(book.getIsbn())){  //책 존재할 경우 그냥 넘어감
//            Book alreadySavedBook = bookRepository.findByIsbn(book.getIsbn())
//                    .orElseThrow(() ->  new
//                            IllegalArgumentException("해당 book이 없습니다."));
//            bookPersonalRequestDto.setBook(alreadySavedBook);
//        }else{
//            bookRepository.save(book);  //존재하지 않을경우 book DB에 저장
//            bookPersonalRequestDto.setBook(book);
//        }
        Book book = saveBookToRepo(isbn);
        bookPersonalRequestDto.setBook(book);

        if(bookPersonalRepository.existsByBookAndUser(bookPersonalRequestDto.getBook(),user)){
            return false;
        }else{
            bookPersonalRequestDto.setUser(user);
            bookPersonalRequestDto.setSaved(LocalDateTime.now());
            bookPersonalRepository.save(bookPersonalRequestDto.toEntity());
            return true;
        }
    }

    @Override
    @Transactional
    public Book saveBookToRepo(String isbn){
        Book book = bookSearchService.searchBooks(isbn);
        if(bookRepository.existsByIsbn(book.getIsbn())){
            book = bookRepository.findByIsbn(book.getIsbn())
                    .orElseThrow(() ->  new
                            IllegalArgumentException("해당 book이 없습니다."));
        } else{
            System.out.println("isbn: "+book.getIsbn());
            bookRepository.save(book);
        }
        return book;
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

        bookMemoRepository.deleteAllByBookPersonal(bookPersonal);
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
                .title(bookMemoRequestDto.getTitle())
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

        //업데이트하면 업데이트한 시간으로 saved가 바뀜
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

    //수정 필요, endDate로만 만들어서 완독 책만 보내주기
    @Override
    @Transactional
    public BookPersonalMonthStatisticsResponseDto findByYear(Long id, String year){
        Integer intYear = Integer.parseInt(year);
        //Integer intMonth = Integer.parseInt(month);
        //현재는 달별 조회인데 달이 넘어가는 책들은 어떻게 해야할지 모르겠다아....
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        LocalDate monthStart = LocalDate.of(intYear, 1, 1);
        LocalDate monthEnd = LocalDate.of(intYear,12,31);
        List<BookPersonal> bookPersonalList = bookPersonalRepository.findAllByUserAndEndDateBetween(user, monthStart, monthEnd);
        List<BookPersonalMonthResponseDto> bookPersonalMonthResponseDtos = new ArrayList<>();
        Integer totalBooks = 0;
        Integer totalReadingPages = 0;

        //System.out.println(bookPersonalList.size());
        /*

        for(BookPersonal bookPersonal: bookPersonalList){
            if(bookPersonal.getBookState() == BookState.DONE){
                totalBooks++;
                totalReadingPages += bookPersonal.getTotalPage();
                totalDays++;
            }
            if(bookPersonal.getBookState() == BookState.READING){
                totalReadingPages += bookPersonal.getReadingPage();
                totalDays++;
            }
        }
        for(BookPersonal bookPersonal: bookPersonalList){
            if(bookPersonal.getBookState() == BookState.DONE){
                bookPersonalMonthResponseDtos.add(new BookPersonalMonthResponseDto(bookPersonal,totalBooks, totalReadingPages, totalDays));
            }
            if(bookPersonal.getBookState() == BookState.READING){
                bookPersonalMonthResponseDtos.add(new BookPersonalMonthResponseDto(bookPersonal,totalBooks, totalReadingPages, totalDays));
            }

         */
        for(BookPersonal bookPersonal: bookPersonalList){
            if(bookPersonal.getBookState() == BookState.DONE){
                totalBooks++;
                totalReadingPages += bookPersonal.getTotalPage();
                bookPersonalMonthResponseDtos.add(new BookPersonalMonthResponseDto(bookPersonal));
            }
        }

        //totalDays는 현재 완성 못함!
        BookPersonalMonthStatisticsResponseDto bookPersonalMonthStatisticsResponseDtos = new BookPersonalMonthStatisticsResponseDto(
                bookPersonalMonthResponseDtos,
                totalBooks,
                totalReadingPages
        );
        return bookPersonalMonthStatisticsResponseDtos;
    }

    @Override
    @Transactional
    public List<BookTopResponseDto> findByTop2(){
        List<Book> bookList = bookPersonalRepository.findBooksTop2ByOrderByBookCountDesc();
        List<BookTopResponseDto> bookTopResponseDtos = new ArrayList<>();
        for(Book book: bookList){
            bookTopResponseDtos.add(new BookTopResponseDto(book));
        }
        return bookTopResponseDtos;
    }

    @Override
    @Transactional
    public List<BookTopResponseDto> findByTop5(){
        List<Book> bookList = bookPersonalRepository.findBooksTop5ByOrderByBookCountDesc();
        List<BookTopResponseDto> bookTopResponseDtos = new ArrayList<>();
        for(Book book: bookList){
            bookTopResponseDtos.add(new BookTopResponseDto(book));
        }
        return bookTopResponseDtos;
    }

    @Override
    @Transactional
    public List<BookTopResponseDto> findByTop10(){
        List<Book> bookList = bookPersonalRepository.findBooksTop10ByOrderByBookCountDesc();
        List<BookTopResponseDto> bookTopResponseDtos = new ArrayList<>();
        for(Book book: bookList){
            bookTopResponseDtos.add(new BookTopResponseDto(book));
        }
        return bookTopResponseDtos;
    }

    @Override
    @Transactional
    public Integer findByMonthCount(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        LocalDate localDate = LocalDate.now();
        LocalDate monthStart = LocalDate.of(localDate.getYear(),
                localDate.getMonth(), 1);
        LocalDate monthEnd = monthStart.plusDays(monthStart.lengthOfMonth()-1);
        Integer count = bookPersonalRepository.countBookPersonalByUserAndBookStateAndEndDateBetween(
                user, BookState.DONE, monthStart, monthEnd);
        return count;
    }

    @Override
    @Transactional
    public boolean checkSavedOrNot(Long id, String isbn){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        String isbnFirst = new String();
        if(isbn.contains(" ")){
            String[] stringList = isbn.split(" ");
            if(stringList[0].isEmpty()){
                isbnFirst = stringList[1];
            }else{
                isbnFirst = stringList[0];
            }
        }else{
            isbnFirst = isbn;
        }
        return bookPersonalRepository.existsByUserAndBook_Isbn(user, isbnFirst);
    }

    @Override
    @Transactional
    public Optional<BookPersonalStateResponseDto> bookPersonalDetail(Long id, String isbn) {
        String isbnFirst = new String();
        if(isbn.contains(" ")){
            String[] stringList = isbn.split(" ");
            if(stringList[0].isEmpty() || stringList[0].contains("X")){
                isbnFirst = stringList[1];
            }else{
                isbnFirst = stringList[0];
            }
        }else{
            isbnFirst = isbn;
        }
        if (checkSavedOrNot(id, isbnFirst)) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
            Book book = bookRepository.findByIsbn(isbnFirst).orElseThrow(
                    () -> new IllegalArgumentException("해당 책이 없습니다.")
            );
            BookPersonal bookPersonal = bookPersonalRepository.findByUserAndBook(user, book).orElseThrow(
                    () -> new IllegalArgumentException("해당 저장된 책이 없습니다.")
            );
            BookResponseDto bookResponseDto = new BookResponseDto(book);
            List<BookMemo> bookMemoList = bookMemoRepository.findByBookPersonalOrderBySavedDesc(bookPersonal);
            List<BookMemoResponseDto> bookMemoResponseDtoList = new ArrayList<>();
            for(BookMemo bookMemo: bookMemoList){
                bookMemoResponseDtoList.add(new BookMemoResponseDto(bookMemo));
            }
            if (checkBookState(bookPersonal) == BookState.DONE) {
                return Optional.of(new BookPersonalDoneStateResponseDto(bookPersonal, bookResponseDto, bookMemoResponseDtoList));
            } else if (checkBookState(bookPersonal) ==  BookState.READING) {
                return Optional.of(new BookPersonalReadingStateResponseDto(bookPersonal, bookResponseDto, bookMemoResponseDtoList));
            } else{
                return Optional.of(new BookPersonalWishStateResponseDto(bookPersonal, bookResponseDto, bookMemoResponseDtoList));
            }
        }
        return Optional.of(new SearchBookResponseDto(bookSearchService.searchBooks(isbn)));
    }

    @Override
    public List<Optional<BookPersonalStateResponseDto>> allstoredBook(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        List<BookPersonal> bookPersonalList = bookPersonalRepository.findByUser(user);
        List<String> isbns = new ArrayList<>();
        for(BookPersonal bookPersonal: bookPersonalList){
            isbns.add(bookPersonal.getBook().getIsbn());
        }
        List<Optional<BookPersonalStateResponseDto>> bookPersonalStateResponseDtos = new ArrayList<>();
        for(String isbn: isbns){
            bookPersonalStateResponseDtos.add(bookPersonalDetail(id, isbn));
        }
        return bookPersonalStateResponseDtos;

    }

    @Override
    public BookState checkBookState(BookPersonal bookPersonal){
        return bookPersonal.getBookState();
    }
}
