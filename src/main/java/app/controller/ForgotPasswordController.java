package app.controller;

import app.entity.ResetToken;
import app.entity.User;
import app.exception.UserNotFoundException;
import app.form.FormReset;
import app.service.MailSenderService;
import app.service.ResetPasswordService;
import app.service.TokenService;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordController {
    private final MailSenderService mailSender;
    private final UserService userService;
    private final ResetPasswordService resetPasswordService;
    private final TokenService tokenService;


    public ForgotPasswordController(MailSenderService mailSender, UserService userService, ResetPasswordService resetPasswordService, TokenService tokenService) {
        this.mailSender = mailSender;
        this.userService = userService;
        this.resetPasswordService = resetPasswordService;
        this.tokenService = tokenService;
    }


    @GetMapping
    public String handle_Get(){
        return "forgot-password";
    }

    @PostMapping
    public RedirectView handle_Post(FormReset formReset, Model model) throws MessagingException {
        String message = "We sent a link for your profile, please click link below and follow the instructor";
        Random random = new Random();
        String token = String.valueOf(random.nextInt(Integer.MAX_VALUE));

        String resetLink = String.format("http://localhost:8585/resetpassword/newpassword?email%s&newtoken%s",
                formReset.getEmail(), token);

        String txt = String.format("%s \n %s", message, resetLink);
        Optional<User> user = userService.findUserByEmail(formReset.getEmail());
        if (user.isPresent()) {
            List<ResetToken> tokens = tokenService.findAllByUser(user.get());
            if (tokens.size()==0){
            resetPasswordService.createToken(formReset.getEmail(), token);}
            else {
                resetPasswordService.updateToken(formReset.getEmail(), token);
            }
            System.err.println("Hereeeeee");
            mailSender.sendMail(formReset.getEmail(),
                    "reset your password",
                    txt);
            model.addAttribute("sent", true);
      }
        else throw new UserNotFoundException();
        return new RedirectView("reset-password") ;
        }


}
