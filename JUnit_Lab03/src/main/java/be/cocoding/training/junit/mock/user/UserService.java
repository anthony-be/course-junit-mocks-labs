package be.cocoding.training.junit.mock.user;

import java.util.Objects;

public class UserService {

    private final UserDao dao;

    public UserService(UserDao dao) {
        this.dao = Objects.requireNonNull(dao, "Given 'dao' parameter cannot be null");
    }

    public User findById(Long id) throws UserNotFoundException {
        Objects.requireNonNull(id, "Given 'id' parameter cannot be null");
        User user = dao.findById(id);
        if(user == null){
            throw new UserNotFoundException("User with id '" + id + "' cannot be found");
        }
        return user;
    }

    public void update(Long id, String username){
        Objects.requireNonNull(id, "Given 'id' parameter cannot be null");
        Objects.requireNonNull(username, "Given 'username' parameter cannot be null");
        User user = dao.findById(id);
        User byUsername = dao.findByUsername(username);
        if(byUsername==null){
            throw new IllegalStateException("Already existing User with username: " + username);
        }
        user.setUsername(username);
        dao.update(user);
    }

}
