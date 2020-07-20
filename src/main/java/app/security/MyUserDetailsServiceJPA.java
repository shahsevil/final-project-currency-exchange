package app.security;

import app.entity.User;
import app.repo.UserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

@Log4j2
@Configuration
public class MyUserDetailsServiceJPA implements UserDetailsService {

    private final UserRepo USER_REPO;

    public MyUserDetailsServiceJPA(UserRepo user_repo) {
        USER_REPO = user_repo;
    }

    public static UserDetails mapper_to_my_ud(User user) {
        return new XUserDetails(
                user.getId(),
                user.getFull_name(),
                user.getPassword()
        ) {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }
        };
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return USER_REPO.findUserByEmail(s)
                .map(MyUserDetailsServiceJPA::mapper_to_my_ud)
                .orElseThrow(() -> {
                    String msg = String.format("User `%s` is not found in the database", s);
                    log.warn(msg);
                    return new UsernameNotFoundException(msg);
                });
    }
}
