package com.xemphim.WebXemPhim.event.listener;

import com.xemphim.WebXemPhim.entity.Account;
import com.xemphim.WebXemPhim.entity.User;
import com.xemphim.WebXemPhim.event.RegistrationCompleteEvent;
import com.xemphim.WebXemPhim.repository.AccountRepository;
import com.xemphim.WebXemPhim.service.JwtService;
import com.xemphim.WebXemPhim.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private User theUser;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // 1. Get the newly registered user
        theUser = event.getUser();
        Account account = accountRepository.findOneByUser(theUser).get();
        //2. Create a verification token for the user
        if(account.isEnabled()) {
            try {
                sendPasswordEmail();
            } catch (MessagingException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        String verificationToken = jwtService.generateToken(account);
        //3. Save the verification token for the user
        userService.saveUserVerificationToken(theUser, verificationToken);
        //4 Build the verification url to be sent to the user
        String url = event.getApplicationUrl() + "/signUp/verifyEmail?token=" + verificationToken;
        //5. Send the email.
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
//        log.info("Click the link to verify your registration :  {}", url);
    }

    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "Hieu Nguyen";
        String mailContent = "<p> Hi, " + theUser.getUserName() + ", </p>" +
                "<p>Thank you for registering with us," + "" +
                "Please, follow the link below to complete your registration.</p>" +
                "<a href=\"" + url + "\">Verify your email to activate your account</a>" +
                "<p> Thank you <br> Hieu Nguyen";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("no-reply@gmail.com", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
    public void sendPasswordEmail() throws MessagingException, UnsupportedEncodingException {
        String pass = UUID.randomUUID().toString();
        Account acc = accountRepository.findOneByUser(theUser).get();
        acc.setPassword(passwordEncoder.encode(pass));
        accountRepository.save(acc);
        String subject = "Email reset password";
        String senderName = "Hieu Nguyen";
        String mailContent = "<p> Hi, " + theUser.getUserName() + ", </p>" +
                "Password reset successful, please use this password to access:</p>" +
                pass +
                "<p> Thank you <br> Hieu Nguyen";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("no-reply@gmail.com", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}
