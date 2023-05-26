package BookMap.PentaRim.User;

import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.User.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
                });
        return new CustomUserDetails(user);
    }

}
