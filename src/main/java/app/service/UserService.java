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

    public Optional<User> findByUserNameAndPassword(String userName, String password) {
        return userRepo.findUserByUserNameAndPassword(userName, password);
    }

    public void registerUser(User user){
        userRepo.save(user);
    }

    public Optional<User> findUserByUserName(String userName){
        return userRepo.findUserByUserName(userName);
    }
}
