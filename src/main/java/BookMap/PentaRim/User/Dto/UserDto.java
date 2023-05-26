package BookMap.PentaRim.User.Dto;



import BookMap.PentaRim.User.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import static BookMap.PentaRim.User.Role.USER;

@Getter
@Setter
@NoArgsConstructor
@Component
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

    public User toEntity(OAuth2User oAuth2User) {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .nickname(this.nickname)
                .role(USER)
                .build();
    }
}
