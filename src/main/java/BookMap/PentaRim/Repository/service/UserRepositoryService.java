package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.User.User;

public interface UserRepositoryService {
    void join(User user);

    User findUser(Long userId);
}
