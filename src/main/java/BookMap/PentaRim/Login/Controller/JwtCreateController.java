package BookMap.PentaRim.Login.Controller;


import BookMap.PentaRim.User.Auth.Filter.JwtProperties;
import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.User.Role;
import BookMap.PentaRim.User.model.User;
import BookMap.PentaRim.User.ouath.provider.GoogleUser;
import BookMap.PentaRim.User.ouath.provider.OAuthUserInfo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



import java.util.Date;
import java.util.Map;

import static BookMap.PentaRim.User.Auth.Filter.JwtProperties.SECRET;


/*
@Slf4j
@RestController
@RequiredArgsConstructor
public class JwtCreateController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/oauth/jwt/google")
    public String jwtCreate(@RequestBody Map<String, Object> data) {
        log.info("jwtCreate 실행됨");
        OAuthUserInfo googleUser =
                new GoogleUser(data);

        User userEntity =
                userRepository.findByUsername(googleUser.getProvider()+"_"+googleUser.getProviderId());
        log.info("userEntity : " + userEntity);

        if(userEntity == null) {
            User user = User.builder()
                    .username(googleUser.getProvider()+"_"+googleUser.getProviderId())
                    .password(bCryptPasswordEncoder.encode(SECRET))
                    .email(googleUser.getEmail())
                    .provider(googleUser.getProvider())
                    .providerId(googleUser.getProviderId())
                    .role(Role.USER)
                    .nickname(googleUser.getName())
                    .picture(googleUser.getPicture())
                    .build();

            log.info( "user : " + user);

            userEntity = userRepository.save(user);

        }

        String jwtToken = JWT.create()
                .withSubject(userEntity.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))
                .withClaim("id", userEntity.getId())
                .withClaim("username", userEntity.getUsername())
                .sign(Algorithm.HMAC512(SECRET));

        log.info(data.toString());

        return jwtToken;
    }
}


 */