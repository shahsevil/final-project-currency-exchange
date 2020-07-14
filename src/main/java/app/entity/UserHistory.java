package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserHistory {
  @Id
  private long u_id;
  private String u_full_name;
  private String u_email;
  private double amount;
  private double rate;
  private LocalDate date_from;
  private LocalDate date_to;
  private LocalDate operation_date;
  private String from_info;
  private String to_info;
}