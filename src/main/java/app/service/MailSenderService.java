package app.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class MailSenderService {
    private final JavaMailSender javaMailSender;

    public MailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String to, String subject, String text) throws MessagingException{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper =  new MimeMessageHelper(mimeMessage, true);

        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(text, true);

        javaMailSender.send(mimeMessage);
    }
//        SimpleMailMessage mail = new SimpleMailMessage();
//        mail.setTo(to);
//        mail.setSubject(subject);
//        mail.setText(text);
//
//        javaMailSender.send(mail);


}
