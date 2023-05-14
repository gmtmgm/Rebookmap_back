package BookMap.PentaRim.Book;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class BookMemo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKPERSONAL_ID")
    private BookPersonal bookPersonal;

    @Column
    private String content;
    @Column
    private LocalDateTime saved;
    @Column
    private Integer page;

    @Builder
    public BookMemo(BookPersonal bookPersonal, String content, LocalDateTime saved, Integer page){
        this.bookPersonal = bookPersonal;
        this.content = content;
        this.saved = saved;
        this.page = page;
    }

}
