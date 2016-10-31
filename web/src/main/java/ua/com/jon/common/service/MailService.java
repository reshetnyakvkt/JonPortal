package ua.com.jon.common.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created with IntelliJ IDEA
 * User: al1
 * Date: 09.06.16
 */

@Service
public class MailService {
    private static final Logger log = Logger.getLogger(MailService.class);

    @Value("${mail.to}")
    private String mailTo;

    @Value("${mail.from}")
    private String mailFrom;

    @Autowired
    private MailSender crunchifymail;

    public void sendEmail(String mailSubject, String msgBody) {
        sendEmail(this.mailTo, mailSubject, msgBody);
    }

    public void sendEmail(String mailTo, String mailSubject, String msgBody) {
        try {
            SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
            crunchifyMsg.setFrom(mailFrom);
            crunchifyMsg.setTo(mailTo);
            crunchifyMsg.setSubject(mailSubject);
            crunchifyMsg.setText(msgBody);
            crunchifymail.send(crunchifyMsg);
        } catch (MailException e) {
            log.error(e);
        }
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        try {
            //
            // Create InternetAddress object and validated the supplied
            // address which is this case is an email address.
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            isValid = true;
        } catch (AddressException e) {
            log.error("You are in catch block -- Exception Occurred for: " + email, e);
        }
        return isValid;
    }
}
