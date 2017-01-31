package com.accion.recruitment.common.helper;

import com.accion.recruitment.common.constants.EmailNotificationConstants;
import com.accion.recruitment.jpa.entities.Settings;
import com.accion.recruitment.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 01/02/17 00:11 AM#$
 */
@Service
public class EmailNotificationHelper {

    public Boolean sendMail(final String To,String From,String subject, String body){

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", EmailNotificationConstants.SMTP_PROTOCOL);
        props.put("mail.smtp.port", EmailNotificationConstants.PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        Session session = Session.getDefaultInstance(props);
        Message msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(From));
            InternetAddress[] toAddresses = { new InternetAddress(To) };
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(body, "text/html");
            Transport transport = session.getTransport();
            transport.connect(EmailNotificationConstants.AWS_HOST, EmailNotificationConstants.AWS_USERNAME, EmailNotificationConstants.AWS_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean sendMail(final List<String> toList,String From,String subject, String body){

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", EmailNotificationConstants.SMTP_PROTOCOL);
        props.put("mail.smtp.port", EmailNotificationConstants.PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        Session session = Session.getDefaultInstance(props);
        Message msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(From));
            InternetAddress[] recipientAddress = new InternetAddress[toList.size()];
            int counter = 0;
            for (String recipient : toList) {
                recipientAddress[counter] = new InternetAddress(recipient.trim());
                counter++;
            }
            msg.setRecipients(Message.RecipientType.TO, recipientAddress);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(body, "text/html");
            Transport transport = session.getTransport();
            transport.connect(EmailNotificationConstants.AWS_HOST, EmailNotificationConstants.AWS_USERNAME, EmailNotificationConstants.AWS_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean sendMail(String to, List<String> bccList,String From,String subject, String body){

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", EmailNotificationConstants.SMTP_PROTOCOL);
        props.put("mail.smtp.port", EmailNotificationConstants.PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        Session session = Session.getDefaultInstance(props);
        Message msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(From));
            InternetAddress[] recipientAddress = new InternetAddress[bccList.size()];
            int counter = 0;
            for (String recipient : bccList) {
                recipientAddress[counter] = new InternetAddress(recipient.trim());
                counter++;
            }
            msg.setRecipients(Message.RecipientType.BCC, recipientAddress);

            InternetAddress[] toAddresses = { new InternetAddress(to) };
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(body, "text/html");
            Transport transport = session.getTransport();
            transport.connect(EmailNotificationConstants.AWS_HOST, EmailNotificationConstants.AWS_USERNAME, EmailNotificationConstants.AWS_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendMail(final String[] to, final String[] cc, final String subject, final String body){

    }

    public void sendMail(final String[] to, final String[] cc, final String[] bcc, final String subject, final String body){

    }
}
