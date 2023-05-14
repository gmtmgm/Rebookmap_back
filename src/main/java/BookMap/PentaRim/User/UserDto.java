package BookMap.PentaRim.User;



import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static BookMap.PentaRim.User.Role.USER;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {


    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private String username;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;

    private String passwordCheck;


    @NotBlank(message = "닉네임이 비어있습니다")
    private String nickname;



    public User toEntity() {
        User user = User.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .role(USER)
                .build();

        return user;
    }

    public User toEntity(String encodedPassword) {
        return User.builder()
                .username(this.username)
                .password(encodedPassword)
                .nickname(this.nickname)
                .role(USER)
                .build();
    }
}
