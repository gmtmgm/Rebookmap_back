package BookMap.PentaRim.ControllerDto;


import BookMap.PentaRim.BookMap.Dto.BookMapResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class UserProfileDto {

     String picture;

     String nickName;

     Long userId;

     String status;

     List<BookMapResponseDto> userBookMapResponseDto;
}
