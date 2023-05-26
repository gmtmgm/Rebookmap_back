package BookMap.PentaRim.BookMap;

import BookMap.PentaRim.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

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
    @NotNull
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
    //@ElementCollection
    //private HashSet<String> hashTag;


    @OneToMany(mappedBy = "bookMap")
    private List<MapHashTag> mapHashTags;

    @Column(name = "BOOKMAP_INDEX")
    private int index;



    @Builder
    public BookMapEntity(Long bookMapId, User user, String bookMapTitle, String bookMapContent){
        this.bookMapId = bookMapId;
        this.user = user;
        this.bookMapTitle = bookMapTitle;
        this.bookMapContent = bookMapContent;
        //this.hashTag = hashTag;
    }

    public void update(String bookMapTitle, String bookMapContent){
        this.bookMapTitle = bookMapTitle;
        this.bookMapContent = bookMapContent;
        //this.hashTag = hashTag;
    }




}
