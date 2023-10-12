package BookMap.PentaRim.Login.Controller;
import BookMap.PentaRim.User.Dto.LoginResponse;
import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.User.Role;
import BookMap.PentaRim.User.model.User;
import BookMap.PentaRim.User.ouath.provider.SessionConst;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.session.Session;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;


@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginGoogleController {

    private final UserRepository userRepository;

    private final JdbcIndexedSessionRepository sessionRepository;





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



    private String MemberLogin(GoogleIdToken.Payload payload, HttpServletRequest request) {

        String email = payload.getEmail();
        boolean emailVerified = payload.getEmailVerified();
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");
        String locale = (String) payload.get("locale");
        String familyName = (String) payload.get("family_name");
        String givenName = (String) payload.get("given_name");

        if (emailVerified) {

            User userEntity =
                    userRepository.findByEmail(email);
            log.info("userEntity : " + userEntity + " 이미 로그인 한 사용자");



          if(userEntity == null) {



              User user = User.builder()
                      .username("google" + "_" + email)
                      .email(email)
                      .provider("google")
                      .providerId(email)
                      .role(Role.USER)
                      .nickname(name)
                      .picture(pictureUrl)
                      .role(Role.USER)
                      .status("")
                      .build();

              log.info("user : " + user);



              //로그인 성공 처리
              //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
              HttpSession session =request.getSession();


              //세션에 로그인 회원 정보 보관
              session.setAttribute(SessionConst.EMAIL, email);



              String sessionId = session.getId();






              /*

              user.setSession(session);



               */

              userRepository.save(user);

             return sessionId;







          } else {





              //로그인 성공 처리
              //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
              HttpSession session =request.getSession();



              //세션에 로그인 회원 정보 보관
              //db에 세션 저장
              session.setAttribute(SessionConst.EMAIL, email);

              //메모리에 저장된 세션 지움 db에 저장된 세션만 남김





              return session.getId();








          }

        }

        log.info("검증되지 않은 토큰입니다");

        Exception e = new Exception();
        throw new RuntimeException(e);



    }





    @PostMapping(value = "/login", produces="application/json; charset=utf8")
    public String Login(@RequestBody  String idToken, HttpServletRequest request ) {
        log.info("로그인 시작 ");
        try{
            GoogleIdToken.Payload idTokenString = decodeIdToken(idToken);
            log.info("id토큰 검증 완료");

            String sessionId = MemberLogin(idTokenString, request);

            log.info("로그인 완료");








            return  sessionId;


        }catch(GeneralSecurityException e){	//FileNotFoundException이 발생했다면
            log.info("알 수 없는 보안 예외");

            throw new RuntimeException(e);


        }catch(IOException e){ //IOException이 발생했다면
            log.info("입출력 예외");
            throw new RuntimeException(e);

        }



    }

    @PostMapping(value = "/logout",produces="application/json; charset=utf8")
    public void logout(@RequestBody String sessionId, HttpServletRequest request) {
        log.info("로그아웃시작");
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        Session savedSession = sessionRepository.findById(sessionId);
        savedSession.isExpired();

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