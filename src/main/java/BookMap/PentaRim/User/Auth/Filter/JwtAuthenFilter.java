package BookMap.PentaRim.User.Auth.Filter;

import BookMap.PentaRim.User.CustomUserDetails;
import BookMap.PentaRim.User.Dto.LoginRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenFilter extends UsernamePasswordAuthenticationFilter {

    /*
    private final AuthenticationManager authenticationManager;

    // Authentication 객체 만들어서 리턴 => 의존 : AuthenticationManager
    // 인증 요청시에 실행되는 함수 => /login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        log.info("JwtAuthenticationFilter : 진입");

        // request에 있는 username과 email을 파싱해서 자바 Object로 받기
        ObjectMapper om = new ObjectMapper();

        LoginRequest loginRequest = null;
        try {
            loginRequest = om.readValue(request.getInputStream(), LoginRequest.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("JwtAuthenticationFilter : "+loginRequest);

        // 유저네임패스워드 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        Objects.requireNonNull(loginRequest).getUsername(),
                        loginRequest.getEmail());

        log.info("authtoken : " + authenticationToken);

        log.info("JwtAuthenticationFilter : 토큰생성완료");


        // authenticate() 함수가 호출 되면 인증 프로바이더가 유저 디테일 서비스의
        // loadUserByUsername(토큰의 첫번째 파라메터) 를 호출하고
        // UserDetails를 리턴받아서 토큰의 두번째 파라메터(credential)과
        // UserDetails(DB값)의 getPassword()함수로 비교해서 동일하면
        // Authentication 객체를 만들어서 필터체인으로 리턴해준다.

        // Tip: 인증 프로바이더의 디폴트 서비스는 UserDetailsService 타입
        // Tip: 인증 프로바이더의 디폴트 암호화 방식은 BCryptPasswordEncoder
        // 결론은 인증 프로바이더에게 알려줄 필요가 없음.
        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);

        log.info("authentication : " + authentication);

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        log.info("Authentication : "+customUserDetails.getUser().getUsername());

        log.info("Authentication : " + customUserDetails.getUser().getUsername());
        return authentication;
    }

    // JWT Token 생성해서 response에 담아주기
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject(customUserDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
                .withClaim("id", customUserDetails.getUser().getId())
                .withClaim("username", customUserDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
    }

     */
}

