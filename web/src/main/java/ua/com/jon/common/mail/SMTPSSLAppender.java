package ua.com.jon.common.mail;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 01.06.15
 */
import java.security.Security;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import org.apache.log4j.net.SMTPAppender;

public class SMTPSSLAppender extends SMTPAppender {

    public SMTPSSLAppender() {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
    }

    @Override
    protected Session createSession() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.host", getSMTPHost());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.quitwait", "false");
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            { return new PasswordAuthentication(getSMTPUsername(),getSMTPPassword()); }
        });
        return session;
    }
}
