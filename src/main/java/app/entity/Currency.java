package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currency")
public class Currency {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "curr_id")
  private long id;

  @Column(name = "curr_symbol")
  private String symbol;

  @Column(name = "curr_name_abv")
  private String abvName;

  @Column(name = "curr_name_long")
  private String longName;

  @JsonIgnore
  @ManyToMany(cascade = CascadeType.ALL,
          mappedBy = "currencies")
  private List<History> histories;

  public Currency(long id) {
    this.id = id;
  }
}
