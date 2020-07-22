package app.service;

import app.entity.User;
import app.exception.DuplicateEmailAddressException;
import app.exception.PasswordDoesntMatchException;
import app.exception.ResetEmptyEx;
import app.dto.FormReg;
import app.repo.UserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class UserService {
    private final UserRepo USER_REPO;
    private final PasswordEncoder PASSWORD_ENC;

    public UserService(UserRepo USER_REPO, PasswordEncoder password_enc) {
        this.USER_REPO = USER_REPO;
        PASSWORD_ENC = password_enc;
    }

    public Optional<User> findUserByEmail(String email) {
        return USER_REPO.findUserByEmail(email);
    }

    public Optional<User> findUserByEmailAndPassword(String userName, String password) {
        return USER_REPO.findUserByEmailAndPassword(userName, password);
    }

//    public boolean registerUser2(FormReg formReg) {
//        if (formReg.getPassword().equals(formReg.getConfirmPassword()) && !findUserByEmail(formReg.getEmail()).isPresent()) {
//            User user = new User(formReg.getFull_name(), formReg.getEmail(), formReg.getPassword());
//            User built = User.builder().full_name(user.getFull_name()).email(user.getEmail()).password(PASSWORD_ENC.encode(user.getPassword())).build();
//            USER_REPO.save(built);
//            log.info("New user successfully registered!");
//            return true;
//        }
//        return false;
//    }

    public void registerUser(FormReg formReg) {
        if (!formReg.getPassword().equals(formReg.getConfirmPassword())) throw new PasswordDoesntMatchException();
        else if (findUserByEmail(formReg.getEmail()).isPresent()) throw new DuplicateEmailAddressException();
        else {
          User user = new User(formReg.getFull_name(), formReg.getEmail(), formReg.getPassword());
          User built = User.builder().full_name(user.getFull_name()).email(user.getEmail()).password(PASSWORD_ENC.encode(user.getPassword())).build();
          USER_REPO.save(built);
          log.info("New user successfully registered!");
        }
    }

    public boolean updatePassword(String email, String password, String confirmpassword) {
        if (email == null || password == null || confirmpassword == null
                || email.isBlank() || password.isBlank() || confirmpassword.isBlank()) throw new ResetEmptyEx();
        else if (!password.equals(confirmpassword)) throw new PasswordDoesntMatchException();
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