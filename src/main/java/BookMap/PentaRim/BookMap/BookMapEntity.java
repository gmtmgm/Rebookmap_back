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
import java.util.List;

@Component
@Getter
@ToString
@Entity
@Table(name = "BookMap")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookMapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKMAP_ID")
    private Long bookMapId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    @Column
    private String bookMapTitle;
    @Column
    private String bookMapContent;
    @Column
    private String bookMapImage; //대표 이미지 첫 책에서 받아오기
    @Column
    private boolean share;
    @Column
    private LocalDateTime bookMapSaveTime;
    @OneToMany(mappedBy = "bookMap")
    private List<MapHashTag> mapHashTags;

    private int scraped;


    @Builder
    public BookMapEntity(Long bookMapId, User user, String bookMapTitle, String bookMapContent, String bookMapImage, boolean share,
                         LocalDateTime bookMapSaveTime){
        this.bookMapId = bookMapId;
        this.user = user;
        this.bookMapTitle = bookMapTitle;
        this.bookMapContent = bookMapContent;
        this.bookMapImage = bookMapImage;
        this.share = share;
        this.bookMapSaveTime = bookMapSaveTime;
        //this.hashTag = hashTag;
        this.scraped = 0;
    }

    public void update(String bookMapTitle, String bookMapContent, String bookMapImage, boolean share){
        this.bookMapTitle = bookMapTitle;
        this.bookMapContent = bookMapContent;
        this.bookMapImage = bookMapImage;
        this.share = share;
        //this.hashTag = hashTag;
    }

    public void update(String bookMapTitle){
        this.bookMapTitle = bookMapTitle;
    }
    public void imageUpdate(String image) {this.bookMapImage = image;}


    public void countScraped() {
        this.scraped++;
    }


}
