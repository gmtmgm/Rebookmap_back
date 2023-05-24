package BookMap.PentaRim.Dto;

import BookMap.PentaRim.Book.Dto.ProfileMemoResponseDto;
import BookMap.PentaRim.User.User;
import lombok.Getter;

import java.util.List;

@Getter
public class ProfileResponseDto {
    private Long id;
    private String nickName;
    //private String state;
    private Integer readBooksCount;
    private List<ProfileMemoResponseDto> profileMemoResponseDtos;

    public ProfileResponseDto(User user, Integer readBooksCount, List<ProfileMemoResponseDto> profileMemoResponseDtos){
        this.id = user.getId();
        this.nickName = user.getNickname();
        this.readBooksCount = readBooksCount;
        this.profileMemoResponseDtos = profileMemoResponseDtos;
    }
}
