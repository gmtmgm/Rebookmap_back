package BookMap.PentaRim.Controller.Subscribe;

import BookMap.PentaRim.ControllerDto.SubscribeDto;
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

    /*

    private final JdbcIndexedSessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Autowired
    SubscribeService subscribeService;

    @PostMapping("/api/subscribe/success")
    public ResponseEntity<String> subscribe(@RequestBody SubscribeDto subscribeDto){

        subscribeService.saveSubscribe(subscribeDto.getToUserId(),
                userRepository.getReferenceById(subscribeDto.getFromUserId()));
        return ResponseEntity.ok().body("팔로우 성공");




    }

    @DeleteMapping("/api/subscribe/do_not")
    public ResponseEntity<String> unSubscribe(@RequestBody SubscribeDto subscribeDto){

        subscribeService.deleteSubscribe(subscribeDto.getToUserId(), subscribeDto.getFromUserId());

        return ResponseEntity.ok().body("팔로우 취소");


    }

     */
}
