package BookMap.PentaRim.User.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchResponseDto {
    private Long id;
    private String nickname;
    private String status;
    private Boolean followOrNot;

    public UserSearchResponseDto(Long id, String nickname, String status, Boolean followOrNot){
        this.id = id;
        this.nickname = nickname;
        this.status = status;
        this.followOrNot = followOrNot;
    }
}
