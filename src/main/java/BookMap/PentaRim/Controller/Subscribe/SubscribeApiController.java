package BookMap.PentaRim.Controller.Subscribe;

import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.User.model.User;
import BookMap.PentaRim.User.ouath.provider.SessionConst;
import BookMap.PentaRim.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.session.Session;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RequiredArgsConstructor
public class SubscribeApiController {

    private final JdbcIndexedSessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Autowired
    SubscribeService subscribeService;

    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<String> subscribe(@PathVariable Long toUserId, @RequestBody String sessionId){

        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            subscribeService.saveSubscribe(toUserId, user);
            return ResponseEntity.ok().body("팔로우 성공");
        }

        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);




    }

    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<String> unSubscribe(@PathVariable Long toUserId,@RequestBody String sessionId ){

        Session findSession = sessionRepository.findById(sessionId);
        if(findSession != null) {

            User user = userRepository.findByEmail(findSession.getAttribute(SessionConst.EMAIL));
            subscribeService.deleteSubscribe(toUserId, user.getId());
            return ResponseEntity.ok().body("팔로우 취소");
        }

        log.info("유효하지 않은 세션입니다");
        Exception e = new Exception();
        throw new RuntimeException(e);



    }
}
