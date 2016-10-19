package ua.com.jon.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA
 * User: al1
 * Date: 09.06.16
 */

@Service
public class MailService {
    @Value("${mail.to}")
    private String mailTo;

    @Value("${mail.from}")
    private String mailFrom;

    @Autowired
    private MailSender crunchifymail;

    public void sendEmail(String mailSubject, String msgBody) {
        SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
        crunchifyMsg.setFrom(mailFrom);
        crunchifyMsg.setTo(mailTo);
        crunchifyMsg.setSubject(mailSubject);
        crunchifyMsg.setText(msgBody);
        crunchifymail.send(crunchifyMsg);
    }
}
