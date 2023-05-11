package BookMap.PentaRim.Book;

import BookMap.PentaRim.User.User;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Setter
@Component
public class BookPersonalRequestDto {

    private User user;
    private Book book;
    private BookState bookState;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer readingPage;
    private Integer totalPage;
    private Float grade;

    @Builder
    public BookPersonalRequestDto(User user, Book book, BookState bookState, LocalDate startDate, Integer readingPage, Integer totalPage){
        this.user = user;
        this.book = book;
        this.bookState = bookState;
        this.startDate = startDate;
        this.readingPage = readingPage;
        this.totalPage = totalPage;
    }



    public BookPersonal toEntity(){
        return BookPersonal.builder()
                .user(user)
                .book(book)
                .bookState(bookState)
                .startDate(startDate)
                .readingPage(readingPage)
                .totalPage(totalPage)
                .build();
    }




}
