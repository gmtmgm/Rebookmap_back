package BookMap.PentaRim.BookMap;

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
    private List<MapHashTag> bookMaps;
/*
   @OneToMany(mappedBy = "hashTag")
   private List<MapHashTag> mapHashTags = new ArrayList<>();


 */


    public HashTag(String hashtag){
        this.tag = hashtag;
    }
/*
    public HashTag(Long id, String hashtag) {
        this.id = id;
        this.hashtag = hashtag;
    }

    public static HashTag of(Long id, String hashtag){
        return new HashTag(id,hashtag);
    }
*/

}
