package ru.geekbrains.winter.market.auth.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MyMailSender {

    private final SimpleMailMessage mailMessage = new SimpleMailMessage();
    private final MailSender mailSender;

    public void regConfirm(String email) {
        mailMessage.setFrom("nik.noreply.b@mail.ru");
        mailMessage.setTo(email);
        mailMessage.setSubject("Подтверждение почты.");
//        String link = "http://localhost:5555/auth/api/v1/users/set_active/" + email;
        String link = "http://95.165.90.118:443/auth/api/v1/users/set_active/" + email;
        mailMessage.setText(String.format("Чтобы подтвердить электронную почту пройдите по ссылке: '%s'", link));
        mailSender.send(mailMessage);
    }
}
