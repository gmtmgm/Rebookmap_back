package BookMap.PentaRim.Dto;

import BookMap.PentaRim.Book.Dto.ProfileMemoResponseDto;
import BookMap.PentaRim.User.model.User;
import lombok.Getter;

import java.util.List;

@Getter
public class ProfileResponseDto {
    private Long id;
    private String nickName;
    private String status;
    private Integer readBooksCount;
    private Integer bookmapCount;
    private String image;
    private List<ProfileMemoResponseDto> profileMemoResponseDtos;

    public ProfileResponseDto(User user, Integer bookmapCount, Integer readBooksCount, String status, List<ProfileMemoResponseDto> profileMemoResponseDtos){
        this.id = user.getId();
        this.nickName = user.getNickname();
        this.status = status;
        this.readBooksCount = readBooksCount;
        this.bookmapCount = bookmapCount;
        this.image = user.getPicture();
        this.profileMemoResponseDtos = profileMemoResponseDtos;
    }
}
