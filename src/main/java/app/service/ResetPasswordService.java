package app.service;

import app.entity.ResetToken;
import app.entity.User;
import app.exception.InvalidTokenEx;
import app.exception.UserNotFoundException;
import app.repo.TokenRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResetPasswordService {
    private final TokenRepo tokenRepo;
    private final UserService userService;

    public ResetPasswordService(TokenRepo tokenRepo, UserService userService) {
        this.tokenRepo = tokenRepo;
        this.userService = userService;
    }

    public List<ResetToken> findAllByUser (User user){
        return tokenRepo.findAllByUser(user);
    }

    public boolean createToken(String email, String token){
        User user = userService.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
        if (token == null) throw  new InvalidTokenEx();
        else{
            ResetToken resetToken = new ResetToken(user, token);
            tokenRepo.save(resetToken);
            return  true;
        }
    }

    public boolean updateToken( String email, String token){
       User user =  userService.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
       if (token == null) throw new InvalidTokenEx();
       else {
           ResetToken resetToken = findAllByUser(user).get(0);
           resetToken.setToken(token);
           tokenRepo.save(resetToken);
           return true;
       }

    }
}
