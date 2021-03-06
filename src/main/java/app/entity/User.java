package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "u_id")
  private long id;

  @Column(name = "u_full_name")
  private String full_name;

  @Column(name = "u_email")
  private String email;

  @Column(name = "password")
  private String password;

  @OneToMany(mappedBy = "user")
  private Set<ResetToken> resetTokens;


  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL,
          mappedBy = "user")
  private Collection<History> histories;

  public User(String full_name, String email, String password) {
    this.full_name = full_name;
    this.email = email;
    this.password = password;
  }

  public User(long id) {
    this.id = id;
  }
}
