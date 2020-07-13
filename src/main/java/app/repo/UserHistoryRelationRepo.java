package app.repo;

import app.entity.History;
import app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHistoryRelationRepo extends JpaRepository<User, History> {

  @Query(value = "select history_id from r_u_h where user_id = ?1", nativeQuery = true)
  List<Long> getHistoryIds(long user_id);


  @Query(value = "SELECT * FROM history as h WHERE h.h_id IN (SELECT history_id FROM r_u_h WHERE user_id = ?1);", nativeQuery = true)
  List<History> findAllHistories(long user_id);
}