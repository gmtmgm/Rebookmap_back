package BookMap.PentaRim.User.model;


import BookMap.PentaRim.User.Role;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class User extends TimeEntity {

    private String provider;
    private String providerId;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String picture;
    private String book_state;
    private String username;
    private String password;
    private String nickname;

    private Role role;
}

