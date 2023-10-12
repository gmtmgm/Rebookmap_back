package BookMap.PentaRim.BookMap;

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
@Table(name = "BookMapDetail")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookMapDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKMAPDETAIL_ID")
    private Long bookMapDetailId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKMAP_ID")
    private BookMapEntity bookMapEntity;
    @Column
    private String type;
    @Column(name = "BOOKMAP_MEMO")
    private String memo;
    @Column(name = "BOOKMAPDETAIL_INDEX")
    private int index;

    private String nickname;

    @Builder
    public BookMapDetailEntity(Long bookMapDetailId, BookMapEntity bookMapEntity, String type, String memo, int index,
                               String nickname){
        this.bookMapDetailId = bookMapDetailId;
        this.bookMapEntity = bookMapEntity;
        this.type = type;
        this.memo = memo;
        this.index = index;
        this.nickname = nickname;
    }

    public void update(String memo, int index){
        this.memo = memo;
        this.index = index;
    }

    public void update(int index){
        this.index = index;
    }

}
