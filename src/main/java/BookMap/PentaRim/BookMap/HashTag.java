package BookMap.PentaRim.BookMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@ToString
@RequiredArgsConstructor
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HASHTAG_ID")
    private Long id;

    @Setter
    @Column(nullable = false)
    private String tag;

    @OneToMany(mappedBy = "hashTag")
    private List<MapHashTag> mapHashTags;

    public HashTag(String hashtag){
        this.tag = hashtag;
    }

}
