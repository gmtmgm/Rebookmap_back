

package BookMap.PentaRim;



import BookMap.PentaRim.User.CustomUserDetails;
import BookMap.PentaRim.User.User;
import BookMap.PentaRim.User.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static BookMap.PentaRim.User.Role.USER;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("getAttributes : {}", oAuth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider + "_" +providerId;

        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user;

        if(optionalUser.isEmpty()) {
            user = User.builder()
                    .username(username)
                    .nickname(oAuth2User.getAttribute("name"))
                    .provider(provider)
                    .providerId(providerId)
                    .role(USER)
                    .build();
            userRepository.save(user);
        } else {
            user = optionalUser.get();
        }

        return new CustomUserDetails(user, oAuth2User.getAttributes());
    }
    }


