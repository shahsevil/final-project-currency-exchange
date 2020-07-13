package app.repo;

import app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
  Optional<User> findUserByEmailAndPassword(String username, String password);

  Optional<User> findUserByEmail(String email);


}