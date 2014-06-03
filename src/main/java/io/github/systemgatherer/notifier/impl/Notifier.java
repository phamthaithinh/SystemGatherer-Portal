package io.github.systemgatherer.notifier.impl;

import com.google.inject.name.Named;
import io.github.systemgatherer.notifier.INotifier;

import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Notifier implements INotifier {

    private final String host;
    private final int port;
    private final String username;
    private final String passwprd;

    @Inject
    public Notifier(@Named("emailHost") String host, @Named("emailPort") int port,
                    @Named("emailUsername") String username, @Named("emailPassword") String passwprd) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.passwprd = passwprd;
    }

    @Override
    public boolean sendEmail(String to, String from, String subject, String body) {
        boolean status = false;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                username,
                                passwprd);
                    }
                }
        );

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            status = true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return status;
    }
}
