package be.cocoding.training.junit.mock.user;

public interface UserDao {

    User findById(Long id);

    boolean existsById(Long id);

    User findByUsername(String username);

    boolean existsByUsername(String username);

    void update(User user);


}
