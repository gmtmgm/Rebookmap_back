package BookMap.PentaRim.Repository;

import BookMap.PentaRim.User.Dto.SubscribeDto;
import BookMap.PentaRim.User.model.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {


    //팔로워 팔로잉 레포지토리 꽤 복잡한 쿼리 갖고잇음.......
    //참고한 자료 출처 오주연에게 문의

    @Modifying
    @Query(value= "insert into Subscribe(toUserId, fromUserId, createDate) VALUES(:toUserId, :fromUserId, now())", nativeQuery = true)
    void saveSubscribe(@Param("toUserId") Long toUserId, @Param("fromUserId") Long fromUserId);


    @Modifying
    @Query(value = "delete from Subscribe where toUserId = :toUserId and fromUserId = :fromUserId", nativeQuery = true)
    void deleteByToUserIdAndFromUserId(@Param("toUserId") Long toUserId, @Param("fromUserId") Long fromUserId);

    Long countByToUserId(Long toUserId);

    boolean existsByToUserIdAndFromUserId(Long toUserId, Long fromUserId);

    @Query("select distinct s from Subscribe s left join fetch s.fromUser where toUserId = :toUserId")
    List<SubscribeDto> findBytoUserId(@Param("toUserId") Long toUserId);

    List<Subscribe> findByFromUserId(Long fromUserId);

}
