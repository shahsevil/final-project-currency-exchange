package app.service;

import app.entity.User;
import app.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> findUserByEmailAndPassword(String userName, String password) {
        return userRepo.findUserByEmailAndPassword(userName, password);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    public void registerUser(String full_name, String email, String password){
        User user = new User(full_name, email, password);
        userRepo.save(user);
    }

}
