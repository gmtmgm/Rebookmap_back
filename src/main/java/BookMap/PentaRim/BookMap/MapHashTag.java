package BookMap.PentaRim.BookMap;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "MAP_TAG")
public class MapHashTag{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAP_TAG_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "HASHTAG_ID")
    private HashTag hashTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKMAP_ID")
    private BookMapEntity bookMap;

    public MapHashTag(HashTag hashTag, BookMapEntity bookMap){
        this.hashTag = hashTag;
        this.bookMap = bookMap;
    }
}
