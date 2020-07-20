//package app.service;
//
//import app.entity.ResetToken;
//import app.entity.User;
//import app.repo.TokenRepo;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TokenService {
//    private final TokenRepo tokenRepo;
//
//    public TokenService(TokenRepo tokenRepo) {
//        this.tokenRepo = tokenRepo;
//    }
//
//    public List <ResetToken>  findAllByUser( User user){
//        return tokenRepo.findAllByUser(user);
//    }
//}
