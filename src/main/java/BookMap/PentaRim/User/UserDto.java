package BookMap.PentaRim.User;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static BookMap.PentaRim.User.Role.USER;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String username;

    private String password;

    private String passwordCheck;

    private String nickname;

    private String email;

    private Role role;

    public User toEntity() {
        User user = User.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .email(email)
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
