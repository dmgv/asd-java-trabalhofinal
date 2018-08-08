package com.javaee.dmgv.projetofinal.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailConfig {

    public Properties getProperties() throws IOException {

        Properties props = new Properties();

        String rootPath = getClass().getClassLoader().getResource("").getPath();
        props.load(new FileInputStream(rootPath + "application.properties"));

        return props;

    }

    private Authenticator configAuth(final String fromMail, final String password) {

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromMail, password);
            }
        };

        return auth;

    }

    private void configHeaders(MimeMessage message) throws MessagingException {

        message.addHeader("Content-Type", "text/HTML; charset=UTF-8");
        message.addHeader("format", "flowed");
        message.addHeader("Content-Transfer-Encoding", "8bit");

    }

    private void configContent(String toMail, String subject, String body, MimeMessage message)
            throws UnsupportedEncodingException, MessagingException {

        message.setFrom(new InternetAddress("diego.menezzes@gmail.com", "Diego M. G. Vieira"));
        message.setReplyTo(InternetAddress.parse("diego.menezzes@gmail.com", false));
        message.setSubject(subject, "UTF-8");
        message.setText(body, "UTF-8");
        message.setSentDate(new Date());
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail, false));

    }

    public void sendMail(String toMail, String subject, String body) {

        try {

            Properties props = getProperties();
            String from = props.getProperty("mail.from");
            String password = props.getProperty("mail.password");

            Authenticator auth = configAuth(from, password);

            Session session = Session.getInstance(props, auth);

            MimeMessage message = new MimeMessage(session);
            configHeaders(message);
            configContent(toMail, subject, body, message);

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        EmailConfig config = new EmailConfig();
        config.sendMail("diego.menezzes@gmail.com", "Subject", "Email Body!");

    }

}
