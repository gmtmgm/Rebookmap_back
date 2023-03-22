package BookMap.PentaRim.Repository;

import BookMap.PentaRim.User.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryImpl implements UserRepository{

    private static Map<Long, User> store = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        store.put(user.getId(), user);
    }

    @Override
    public User findById(Long id) {
        return store.get(id);
    }
}
