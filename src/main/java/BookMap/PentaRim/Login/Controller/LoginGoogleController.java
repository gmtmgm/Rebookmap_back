package BookMap.PentaRim.Login.Controller;
import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.User.Role;
import BookMap.PentaRim.User.model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static BookMap.PentaRim.User.Auth.Filter.JwtProperties.SECRET;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginGoogleController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/login/google")
    public void loginGoogle(@RequestParam("idToken") String idTokenString) throws GeneralSecurityException, IOException {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList("205578501902-2g0aj7mkvmtbpqdcjqt86nhc64euj7l0.apps.googleusercontent.com"))
                .build();


        // (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");


            // Use or store profile information

            if(emailVerified) {

            User userEntity =
                    userRepository.findByUsername("google" + "_" + email);
            log.info("userEntity : " + userEntity);

            if (userEntity == null) {
                User user = User.builder()
                        .username("google" + "_" + email)
                        .password(bCryptPasswordEncoder.encode(SECRET))
                        .email(email)
                        .provider("google")
                        .providerId(email)
                        .role(Role.USER)
                        .nickname(name)
                        .picture(pictureUrl)
                        .build();

                log.info("user : " + user);

                userRepository.save(user);
            } else {
                log.info("Invalid email");
            }


                // ...

            } else {
                log.info("Invalid ID token.");
            }
        }
    }
}
