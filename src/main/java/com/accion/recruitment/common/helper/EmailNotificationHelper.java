package com.accion.recruitment.common.helper;

import com.accion.recruitment.jpa.entities.Settings;
import com.accion.recruitment.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 01/02/17 00:11 AM#$
 */
@Service
public class EmailNotificationHelper {


    @Autowired
    private SettingsService settingsService;

    final static String AWS_USERNAME = "AKIAIBBLVFHFEDJCGRZA";
    final static String AWS_PASSWORD = "AiS2Cn9JsZPyoVYCX3Wuwi3SdA7YQ2zjcMCxoNHK+glm";
    final static String AWS_HOST = "email-smtp.us-east-1.amazonaws.com";
    final static int PORT = 587;
    final static String SMTP_PROTOCOL="smtp";
    final static String LOGIN_URL="/RecruitmentWeb/myProfile.do#changePassword";
    final static String IMAGE_SRC="https://d2ue829my6ap69.cloudfront.net/images/AccionPNG.png";
    String footer="";

    public Boolean sendMail(final String to,String subject, final String body){

        HashMap<String,String> settingMap=this.getSettingsMap();
        String FROM=settingMap.get("from");
        subject+=settingMap.get("footer");

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", SMTP_PROTOCOL);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        Session session = Session.getDefaultInstance(props);
        Message msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] toAddresses = { new InternetAddress(to) };
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(body, "text/html");
            Transport transport = session.getTransport();
            transport.connect(AWS_HOST, AWS_USERNAME, AWS_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public HashMap<String,String> getSettingsMap(){

        Settings settings=this.settingsService.getSettingsDetailsById(1);

        HashMap<String,String> settingMap=new HashMap<String, String>();

        footer += "Link: <a href=\""+settings.getDomainName()+LOGIN_URL+"> Hooked On Talent </a> <br/><br/><br/>";
        footer += "Thanks,<br/>Team Accion Labs<br/>";
        footer +="<a href=\"http://www.accionlabs.com\"><img src="+IMAGE_SRC+" height=\"40\" alt=\"\" /></a>";
        footer += "</html>";

        settingMap.put("from",settings.getEmailId());
        settingMap.put("footer",footer);

        return settingMap;

    }




    public void sendMail(final String[] to, final String subject, final String body){

    }

    public void sendMail(final String[] to, final String[] cc, final String subject, final String body){

    }

    public void sendMail(final String[] to, final String[] cc, final String[] bcc, final String subject, final String body){

    }
}
