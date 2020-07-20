package app.repo;

import app.entity.History;
import app.entity.User;
import app.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHistoryRelationRepo extends JpaRepository<UserHistory, Long> {

  @Query(value = "SELECT DISTINCT h.h_id,\n" +
          "                u.u_full_name,\n" +
          "                u.u_email,\n" +
          "                h.amount,\n" +
          "                h.rate,\n" +
          "                h.date_from,\n" +
          "                h.date_to,\n" +
          "                h.operation_date,\n" +
          "                fromC.curr_name_abv || '-' ||\n" +
          "                fromC.curr_name_long as from_info,\n" +
          "                toC.curr_name_abv || '-' ||\n" +
          "                toC.curr_name_long as to_info \n" +
          "from users u\n" +
          "         inner join r_u_h ruh on u.u_id = ruh.user_id\n" +
          "         inner join history h on ruh.history_id = h.h_id\n" +
          "         inner join r_c_h rch on h.h_id = rch.history_id\n" +
          "         inner join currency fromC on h.curr_from_id = fromC.curr_id\n" +
          "         inner join currency toC on h.curr_to_id = toC.curr_id \n" +
          "where user_id = (?1);", nativeQuery = true)
  List<UserHistory> findAllHistories(long id);
}