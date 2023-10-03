package BookMap.PentaRim.Login.Controller;
import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.User.Role;
import BookMap.PentaRim.User.model.User;
import BookMap.PentaRim.User.ouath.provider.SessionConst;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

import static BookMap.PentaRim.User.Auth.Filter.JwtProperties.SECRET;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginGoogleController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;




    private GoogleIdToken.Payload decodeIdToken(String idTokenString) throws GeneralSecurityException, IOException {
        HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory gsonFactory = GsonFactory.getDefaultInstance();
        GoogleIdTokenVerifier verifier =
                new GoogleIdTokenVerifier.Builder(transport,gsonFactory)
                        .setAudience(Collections.singletonList("205578501902-2g0aj7mkvmtbpqdcjqt86nhc64euj7l0.apps.googleusercontent.com"))
                        .build();



        GoogleIdToken idToken = verifier.verify(idTokenString);
        return idToken.getPayload();
    }




    private HttpServletResponse MemberLogin(GoogleIdToken.Payload payload, HttpServletRequest request, HttpServletResponse
                                            response) {

        String email = payload.getEmail();
        boolean emailVerified = payload.getEmailVerified();
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");
        String locale = (String) payload.get("locale");
        String familyName = (String) payload.get("family_name");
        String givenName = (String) payload.get("given_name");

        if (emailVerified) {

            User userEntity =
                    userRepository.findByUsername("google" + "_" + email);
            log.info("userEntity : " + userEntity);



          if(userEntity == null) {



              User user = User.builder()
                      .username("google" + "_" + email)
                      .email(email)
                      .provider("google")
                      .providerId(email)
                      .role(Role.USER)
                      .nickname(name)
                      .picture(pictureUrl)
                      .build();

              log.info("user : " + user);

              //로그인 성공 처리
              //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
              HttpSession session =request.getSession();
              //세션에 로그인 회원 정보 보관
              session.setAttribute(SessionConst.LOGIN_MEMBER, email);

              user.setSession(session);



              userRepository.save(user);

              Cookie cookie = new Cookie("session", session.getId());
              response.addCookie(cookie);

              return response;





          } else {


              //로그인 성공 처리
              //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
              HttpSession session =request.getSession();
              //세션에 로그인 회원 정보 보관
              session.setAttribute(SessionConst.LOGIN_MEMBER, email);

              userEntity.setSession(session);

              userRepository.save(userEntity);

              Cookie cookie = new Cookie("session", session.getId());
              response.addCookie(cookie);

              return response;


          }

        }

        log.info("검증되지 않은 토큰입니다");

        Exception e = new Exception();
        throw new RuntimeException(e);



    }




    @PostMapping(value = "/login", produces="application/json; charset=utf8")
    public void Login( @RequestBody  String idToken, HttpServletRequest request ,HttpServletResponse response) {
        log.info("로그인 시작 ");
        try{
            GoogleIdToken.Payload idTokenString = decodeIdToken(idToken);
            log.info("id토큰 검증 완료");

            MemberLogin(idTokenString, request, response);
            log.info("로그인 완료");


        }catch(GeneralSecurityException e){	//FileNotFoundException이 발생했다면
            log.info("알 수 없는 보안 예외");


        }catch(IOException e){ //IOException이 발생했다면
            log.info("입출력 예외");


        }



    }

    @PostMapping(value = "/logout",produces="application/json; charset=utf8")
    public void logout(HttpServletRequest request) {
        log.info("로그아웃시작");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

    }


    
}

/*
    @PostMapping("/login")
    private void loginGoogle(@RequestBody HashMap<String, String> idTokenString, HttpServletRequest request) throws GeneralSecurityException, IOException {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList("205578501902-2g0aj7mkvmtbpqdcjqt86nhc64euj7l0.apps.googleusercontent.com"))
                .build();


        // (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = verifier.verify(String.valueOf(idTokenString));
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

   */