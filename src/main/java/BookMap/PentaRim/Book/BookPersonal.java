package BookMap.PentaRim.Book;

import BookMap.PentaRim.User.User;
import BookMap.PentaRim.memo.Memo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Component
@Table(
        name="book_personal",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UniqueUserAndBook",
                        columnNames=  {"user_id", "book_id"}
                ),
        }
)
public class BookPersonal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKPERSONAL_ID")
    private Long id;
    //이야기 해봐야함, book에 안넣는다면 따로 이런식으로?

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;  //사용자 아이디

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Column
    @Enumerated(EnumType.STRING)
    private BookState bookState;

    //@Embedded
    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private Integer readingPage;
    @Column
    private Integer totalPage;

    @Column
    private Float grade;  //별점

    //빌더 필요

    @Builder
    public BookPersonal(User user, Book book, BookState bookState, LocalDate startDate, LocalDate endDate, Integer readingPage, Integer totalPage, Float grade){
        this.user = user;
        this.book = book;
        this.bookState = bookState;
        this.startDate = startDate;
        this.endDate = endDate;
        this.readingPage = readingPage;
        this.totalPage = totalPage;
        this.grade = grade;
    }

    public void update(BookState bookState, LocalDate startDate, LocalDate endDate, Integer readingPage, Integer totalPage, Float grade){
        this.bookState = bookState;
        this.startDate = startDate;
        this.endDate = endDate;
        this.readingPage = readingPage;
        this.totalPage = totalPage;
        this.grade = grade;
    }

    public void updateState(BookState bookState){
        this.bookState = bookState;
    }

}