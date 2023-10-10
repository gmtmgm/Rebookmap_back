package BookMap.PentaRim.ControllerDto;


import BookMap.PentaRim.Dto.ProfileUpdateRequestDto;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

@Data
public class ProfileUpdateDto {

    ProfileUpdateRequestDto profileUpdateRequestDto;
    String sessionId;
}
