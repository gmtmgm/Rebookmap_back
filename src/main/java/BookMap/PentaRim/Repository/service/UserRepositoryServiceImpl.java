package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.Repository.MemoRepository;
import BookMap.PentaRim.Repository.MemoRepositoryImpl;
import BookMap.PentaRim.Repository.UserRepository;
import BookMap.PentaRim.Repository.UserRepositoryImpl;
import BookMap.PentaRim.User.User;
import BookMap.PentaRim.memo.Memo;

public class UserRepositoryServiceImpl {

    private final UserRepository userRepository = new
            UserRepositoryImpl();
    public void join(User user) {
        userRepository.save(user);
    }
    public User findUser(Long userId) {
        return userRepository.findById(userId);
    }
}
