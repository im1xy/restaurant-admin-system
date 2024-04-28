package Mailer;

import LoggerManager.LoggerManager;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Properties;

public class Mailer {
    private final String mailerProperties = "./src/main/resources/properties/mailer.properties";
    private final String mailerCredentials = "./src/main/resources/properties/mailerCredentials.properties";
    private String botAddress;
    private String botPassword;
    private String devAddress;
    private Properties properties;
    private Session session;
    public Mailer() {
        properties = this.loadProperties();
        this.setCredentials();
        this.createSession();
    }

    private Properties loadProperties() {
        try {
            Properties props = new Properties();
            FileInputStream mailer = new FileInputStream(mailerProperties);
            props.load(mailer);
            return props;
        } catch (FileNotFoundException e) {
            LoggerManager.logger.severe("Failed to Find Mailer Properties from: " + mailerCredentials);
            throw new RuntimeException("Could not find mailer.properties");
        } catch (IOException e) {
            LoggerManager.logger.severe("Failed to Load Mailer Properties from: " + mailerCredentials);
            throw new RuntimeException(e);
        }
    }

    private void setCredentials() {
        Properties credentials = loadCredentials();
        botAddress = credentials.getProperty("BOT_ADDRESS");
        botPassword = credentials.getProperty("BOT_PASSWORD");
        devAddress = credentials.getProperty("DEV_ADDRESS");
    }

    private Properties loadCredentials() {
        try {
            FileInputStream credentials = new FileInputStream(mailerCredentials);
            Properties properties = new Properties();
            properties.load(credentials);

            LoggerManager.logger.info("Mailer Credentials loaded successfully");
            return properties;
        } catch (IOException e) {
            LoggerManager.logger.severe("Failed to Load Mailer Credentials from: " + mailerCredentials);
            throw new RuntimeException(e);
        }
    }

    private void createSession() {
        this.session = Session.getInstance(this.properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(botAddress, botPassword);
                    }
                });
    }

    public int sendMessage(String subject, String messageText, File attachment) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(botAddress));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(devAddress)
            );

            message.setSubject("BOT: " + subject);
            message.setContent(this.createMail(messageText, attachment));
            Transport.send(message);
            LoggerManager.logger.info("Email sent successfully");
            return 1;
        } catch (MessagingException | IOException e) {
            LoggerManager.logger.severe("Failed to send Error Email");
        }
        return 0;
    }

    private Multipart createMail(String messageText, File attachment) throws MessagingException, IOException {
        Multipart multipart = new MimeMultipart();
        BodyPart textBody = new MimeBodyPart();
        textBody.setText(messageText);
        multipart.addBodyPart(textBody);

        if (attachment != null) {
            MimeBodyPart attachmentBody = new MimeBodyPart();
            attachmentBody.attachFile(attachment);
            multipart.addBodyPart(attachmentBody);
        }

        return multipart;
    }
}