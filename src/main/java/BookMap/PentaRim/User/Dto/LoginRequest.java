package BookMap.PentaRim.User.Dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {

    private String username;

    private String password;
}
