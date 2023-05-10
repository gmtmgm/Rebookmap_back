package BookMap.PentaRim.service;

import BookMap.PentaRim.User.UserRequestDto;
import BookMap.PentaRim.User.UserResponseDto;
import BookMap.PentaRim.User.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



public interface UserService {

    Long save(UserRequestDto userRequestDto);

    Long update(Long id, UserUpdateRequestDto userUpdateRequestDto);

    UserResponseDto findByID (Long id);

    //Long findByUID(String uid);

}
