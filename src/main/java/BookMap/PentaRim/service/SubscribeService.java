package BookMap.PentaRim.service;

import BookMap.PentaRim.Repository.SubscribeRepository;
import BookMap.PentaRim.User.Dto.SubscribeDto;
import BookMap.PentaRim.User.model.Subscribe;
import BookMap.PentaRim.User.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class SubscribeService {

    //팔로잉 팔로워 서비스

    @Autowired
    SubscribeRepository subscribeRepository;


    @Transactional
    public void saveSubscribe(Long toUserId, User fromUser) {


        Subscribe subscribe = new Subscribe(toUserId, fromUser);

        subscribeRepository.save(subscribe);

    }



    @Transactional
    public void deleteSubscribe(Long toUserId, Long fromUserId) {

        subscribeRepository.deleteByToUserIdAndFromUserId(toUserId, fromUserId);


    }

    public List<SubscribeDto> subscribeList(Long toUserId, Long principalId) {
        List<SubscribeDto> subscribeDto = subscribeRepository.findBytoUserId(toUserId);
        List<Subscribe> subscribeList = subscribeRepository.findByFromUserId(principalId);

        for(Subscribe sub : subscribeList) {
            for(SubscribeDto dto : subscribeDto) {
                if(Objects.equals(sub.getToUserId(), dto.getId()))
                    dto.setSubscribeState(true);
            }

        }
        return subscribeDto;
    }
}
