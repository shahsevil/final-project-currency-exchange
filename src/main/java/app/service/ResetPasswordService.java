//package app.service;
//
//import app.entity.ResetToken;
//import app.entity.User;
//import app.exception.InvalidTokenEx;
//import app.exception.ResetEmptyEx;
//import app.exception.UserNotFoundException;
//import app.repo.TokenRepo;
//import app.repo.UserRepo;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ResetPasswordService {
//    private final TokenRepo tokenRepo;
//    private final UserService userService;
//    private final UserRepo userRepo;
//
//    public ResetPasswordService(TokenRepo tokenRepo, UserService userService, UserRepo userRepo) {
//        this.tokenRepo = tokenRepo;
//        this.userService = userService;
//        this.userRepo = userRepo;
//    }
//
//    public List<ResetToken> findAllByUser (User user){
//        return tokenRepo.findAllByUser(user);
//    }
//
//    public boolean createToken(String email, String token){
//        User user = userService.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
//        if (token == null) throw  new InvalidTokenEx();
//        else{
//            ResetToken resetToken = new ResetToken(user, token);
//            tokenRepo.save(resetToken);
//            return  true;
//        }
//    }
//
//    public boolean updateToken( String email, String token){
//       User user =  userService.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
//       if (token == null) throw new InvalidTokenEx();
//       else {
//           ResetToken resetToken = findAllByUser(user).get(0);
//           resetToken.setToken(token);
//           tokenRepo.save(resetToken);
//           return true;
//       }
//
//    }
//
//    public boolean validateToken(String email, String token) {
//        if(email == null || token == null || email.isBlank() || token.isBlank()) throw new ResetEmptyEx();
//        User user = userRepo.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
//
//        return tokenRepo.findAllByUser(user).get(0).getToken().equals(token);
//    }
//}
