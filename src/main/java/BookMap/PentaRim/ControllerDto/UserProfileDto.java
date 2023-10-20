package BookMap.PentaRim.ControllerDto;


import BookMap.PentaRim.BookMap.Dto.BookMapResponseDto;
import BookMap.PentaRim.BookMap.Dto.UserBookMapResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class UserProfileDto {

     String picture;

     String nickName;

     Long userId;

     String status;

     List<UserBookMapResponseDto> userBookMapResponseDto;

}
