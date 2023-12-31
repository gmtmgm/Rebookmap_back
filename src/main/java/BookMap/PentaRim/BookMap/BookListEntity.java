package BookMap.PentaRim.BookMap;

import BookMap.PentaRim.Book.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Entity
@Table(name = "BookList")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKLIST_ID")
    private Long bookListId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKMAPDETAIL_ID")
    private BookMapDetailEntity bookMapDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Column(name = "BOOKLIST_INDEX")
    private int index;

    @Builder
    public BookListEntity(Long bookListId, BookMapDetailEntity bookMapDetail, Book book, int index){
        this.bookListId = bookListId;
        this.bookMapDetail = bookMapDetail;
        this.book = book;
        this.index = index;
    }

    public void update(Book book, int index){
        this.book = book;
        this.index = index;
    }
}
