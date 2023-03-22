package BookMap.PentaRim.Repository;

import BookMap.PentaRim.User.User;

public interface UserRepository {

    void save(User user);

    User findById(Long id);
}
