package app.repo;

import app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByUserNameAndPassword(String username, String password);
//    void saveUser(User user);

    Optional<User> findUserByEmail(String email);
}
