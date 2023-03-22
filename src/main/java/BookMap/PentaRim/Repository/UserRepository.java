package BookMap.PentaRim.Repository;

import BookMap.PentaRim.User.User;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository {

    void save(User user);

    User findById(Long id);
}
