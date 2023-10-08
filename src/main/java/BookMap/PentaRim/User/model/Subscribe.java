package BookMap.PentaRim.User.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name="subscribe_uk",
                        columnNames = {"toUserId", "fromUserId"}
                )
        }
)
public class Subscribe {

    //팔로잉 팔로워 기능

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


    private Long toUserId;

    @JoinColumn(name = "fromUserId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User fromUser;


    @CreationTimestamp
    private Timestamp createDate;

    // JPQL 사용하지 않을시
    public Subscribe(Long toUserId, User fromUser) {
        this.toUserId = toUserId;
        this.fromUser = fromUser;
    }

}
