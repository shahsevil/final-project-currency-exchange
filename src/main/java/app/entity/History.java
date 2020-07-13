package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "history")
public class History {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "h_id")
  private long id;

  @Column(name = "curr_from_id")
  private long fromId;

  @Column(name = "curr_to_id")
  private long toId;

  @Column(name = "date_from")
  private LocalDate dateFrom;

  @Column(name = "date_to")
  private LocalDate dateTo;

  @Column(name = "operation_date")
  private LocalDate operationDate;

  @Column(name = "rate")
  private double rate;

  @Column(name = "amount")
  private double amount;

  @JsonIgnore
  @ManyToMany
  @JoinTable(name = "r_c_h",
          joinColumns = {@JoinColumn(name = "history_id", referencedColumnName = "h_id")},
          inverseJoinColumns = {@JoinColumn(name = "currency_id", referencedColumnName = "curr_id")})
  private Collection<Currency> currencies;

  @JsonIgnore
  @ManyToOne
  @JoinTable(name = "r_u_h",
          joinColumns = {@JoinColumn(name = "history_id", referencedColumnName = "h_id")},
          inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "u_id")})
  private User user;

  public History(long fromId, long toId, LocalDate dateFrom, LocalDate dateTo, LocalDate operationDate,
                 double rate, double amount, Collection<Currency> currencies, User user) {
    this.fromId = fromId;
    this.toId = toId;
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
    this.operationDate = operationDate;
    this.rate = rate;
    this.amount = amount;
    this.currencies = currencies;
    this.user = user;
  }

}
