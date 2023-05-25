package BookMap.PentaRim.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@Setter
@Component
public class UserRequestDto {
    private String nickname;
}
