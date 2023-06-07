package BookMap.PentaRim.BookMap;

import BookMap.PentaRim.User.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@ToString
@Entity
@Table(name = "BookMapScrap")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookMapScrapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKMAPSCRAP_ID")
    private Long bookMapScrapId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKMAP_ID")
    private BookMapEntity bookMap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    @Column
    private LocalDateTime bookMapSaveTime;



    @Builder
    public BookMapScrapEntity(Long bookMapScrapId, BookMapEntity bookMap, User user, LocalDateTime bookMapSaveTime){
        this.bookMapScrapId = bookMapScrapId;
        this.bookMap = bookMap;
        this.user = user;
        this.bookMapSaveTime = bookMapSaveTime;
    }




}
