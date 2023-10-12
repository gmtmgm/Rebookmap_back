package BookMap.PentaRim.User.Repository;

import BookMap.PentaRim.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByNickname(String nickname);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findByNicknameContaining(String keyword);



}
