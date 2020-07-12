package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reset_token")
public class ResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rtoken_id")
    private long id;
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "r_reset_user",
            joinColumns = {@JoinColumn(name="reset_id",
                    referencedColumnName = "rtoken_id"),
            },
            inverseJoinColumns = {@JoinColumn(name = "user_id",
                    referencedColumnName = "u_id")})
    private User user;

    public ResetToken(User user, String token) {
        this.user = user;
        this.token = token;
    }

}
