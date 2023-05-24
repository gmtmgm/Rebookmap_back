package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Book.BookState;
import BookMap.PentaRim.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Setter
@Component
public class BookPersonalRequestDto {

    private User user;
    private Book book;
    private String bookState;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer readingPage;
    private Integer totalPage;
    private Float grade;

    private LocalDateTime saved;

    @Builder
    public BookPersonalRequestDto(User user, Book book, String bookState, LocalDate startDate, LocalDate endDate, Integer readingPage, Integer totalPage,
                                  LocalDateTime saved){
        this.user = user;
        this.book = book;
        this.bookState = bookState;
        this.startDate = startDate;
        this.endDate = endDate;
        this.readingPage = readingPage;
        this.totalPage = totalPage;
        this.saved = saved;
    }


    public BookPersonal toEntity(){
        return BookPersonal.builder()
                .user(user)
                .book(book)
                .bookState(BookState.from(bookState))
                .startDate(startDate)
                .endDate(endDate)
                .readingPage(readingPage)
                .totalPage(totalPage)
                .saved(saved)
                .build();
    }








}
