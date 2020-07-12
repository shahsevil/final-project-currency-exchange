package app.service;

import app.entity.User;
import app.exception.PasswordDoesntMatchException;
import app.exception.ResetEmptyEx;
import app.repo.UserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class UserService {
    private final UserRepo USER_REPO;

    public UserService(UserRepo USER_REPO) {
        this.USER_REPO = USER_REPO;
    }

    public Optional<User> findUserByEmail(String email) {
        return USER_REPO.findUserByEmail(email);
    }

    public Optional<User> findUserByEmailAndPassword(String userName, String password) {
        return USER_REPO.findUserByEmailAndPassword(userName, password);
    }


    public void registerUser(String full_name, String email, String password) {
        User user = new User(full_name, email, password);
        USER_REPO.save(user);
        log.info("New user successfully registered!");
    }

    public boolean updatePassword(String email, String password, String confirmpassword) {
        if (email == null || password ==null || confirmpassword == null
                ||  email.isBlank() || password.isBlank() || confirmpassword.isBlank()) throw new ResetEmptyEx();
        else if (!password.equals(confirmpassword)) throw  new PasswordDoesntMatchException();
        else {
          Optional<User> user = findUserByEmail(email);
          //
          //userRepo.save(user);
           return true;

        }

    }

    public Optional<User> findUserById(long user_id) {
        return USER_REPO.findById(user_id);
    }
}