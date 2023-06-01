package BookMap.PentaRim.User.model;


import BookMap.PentaRim.User.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class User extends TimeEntity {

    @NotBlank
    private String provider;
    @NotBlank
    @Column(unique = true)
    private String providerId;


    @NotBlank
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String email;
    private String picture;
    private String book_state;

    @NotBlank
    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;

    @NotBlank
    private Role role;
}

