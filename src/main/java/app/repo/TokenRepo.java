package app.repo;

import app.entity.ResetToken;
import app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepo extends JpaRepository<ResetToken, String> {
    List<ResetToken> findAllByUser (User user);

}
