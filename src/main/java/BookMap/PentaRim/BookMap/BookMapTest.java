package BookMap.PentaRim.BookMap;

import BookMap.PentaRim.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Component
public class BookMapTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "BOOKMAPTEST_ID")
    private Long id;

    @OneToMany(mappedBy = "bookMapTest")
    private List<MapHashTag> mapHashTags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

}
