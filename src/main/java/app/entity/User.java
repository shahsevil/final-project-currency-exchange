package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    private long id;
    private String full_name;
    private String email;
    private String password;

    public User(String full_name, String email, String password) {
        this.full_name = full_name;
        this.email = email;
        this.password = password;
    }
}
