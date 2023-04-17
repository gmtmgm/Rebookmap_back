package BookMap.PentaRim.Repository.service;
import BookMap.PentaRim.Repository.UserRepository;
import BookMap.PentaRim.User.User;


public class UserRepositoryServiceImpl {

    private final UserRepository userRepository;

    public UserRepositoryServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public void join(User user) {
        userRepository.save(user);
    }
    public User findUser(Long userId) {
        return userRepository.findById(userId);
    }
}
