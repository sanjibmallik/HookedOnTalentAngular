package com.accion.recruitment.service.impl;

import com.accion.recruitment.common.constants.EmailNotificationConstants;
import com.accion.recruitment.common.helper.EmailNotificationHelper;
import com.accion.recruitment.jpa.entities.Settings;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.UserEmailNotificationService;
import com.accion.recruitment.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 01/02/17 00:11 AM#$
 */
@Service("emailNotificationService")
public class UserEmailNotificationServiceImpl implements UserEmailNotificationService {

    @Autowired
    private EmailNotificationHelper emailNotificationHelper;

    @Autowired
    private SettingsService settingsService;

    public  Boolean  sendUserCredentials(User user){
        try{
            String subject = "HookedOn Talent Login Credentials";
            String body = "<html><head>";
            body += "<style>table {border-collapse: collapse;} table, td, th {border: 2px solid black;}</style></head>";
            body += "Hello "+user.getFirstName()+"<br/>";
            body += "Congratulation!! <br/>";
            body += "You are now a part of HookedOn Talent App <br/>";
            body += "Your User Id and Password has been created and the credentials are as follows <br/><br/>";
            body += "UserName : "+user.getUserName();
            body += "<br/>";
            body += "Password :"+user.getPassword();
            body += "<br/></br>";
            body += "Please login to the application and change the password using the application link provided below..<br/></br>";
            body+=this.getSettingsMap().get("link");
            body+= EmailNotificationConstants.SIGNATURE;
            return this.emailNotificationHelper.sendMail(user.getEmailId(),this.getSettingsMap().get("from"),subject,body);
        }catch (Exception e){
            return false;
        }
    }

    public  Boolean  sendUserDisableStatus(User user){
        String subject = "HookedOn Talent notification";
        String body = "<html><head>";
        body += "<style>table {border-collapse: collapse;} table, td, th {border: 2px solid black;}</style></head>";
        body += "Hello "+user.getFirstName()+",<br/><br/>";
        body += "This is a system generated email to inform you that your account has been deactivated in HoT- HookedOn Talent Application<br/><br/>";
        body += "Your login credentials will be non-functional till next update. <br/><br/>";
        body += "Please contact admin for further details.<br/><br/>";
        body+= EmailNotificationConstants.SIGNATURE;
        return this.emailNotificationHelper.sendMail(user.getEmailId(),this.getSettingsMap().get("from"),subject,body);

    }

    public  Boolean  sendUserEnableStatus(User user){
        String subject = "HookedOn Talent notification";
        String body = "<html><head>";
        body += "<style>table {border-collapse: collapse;} table, td, th {border: 2px solid black;}</style></head>";
        body += "Hello "+user.getFirstName()+",<br/><br/>";
        body += "This is a system generated email to inform you that your account has been Activated in HoT- HookedOn Talent Application<br/><br/>";
        body += "Your login credentials are enabled for HoT-HookedOn Talent Application.<br/><br/>";
        body += "Please login to the application using your user id & password using the application link provided below.<br/></br>";
        body+=this.getSettingsMap().get("link");
        body+= EmailNotificationConstants.SIGNATURE;
        return this.emailNotificationHelper.sendMail(user.getEmailId(),this.getSettingsMap().get("from"),subject,body);

    }


    public HashMap<String,String> getSettingsMap(){
        Settings settings=this.settingsService.getSettingsDetailsById(1);
        HashMap<String,String> settingMap=new HashMap<String, String>();
        String link= "Link: <a href=\""+settings.getDomainName()+""+EmailNotificationConstants.CHANGE_PASSWORD_URL+"\">"+settings.getDomainName()+""+EmailNotificationConstants.CHANGE_PASSWORD_URL+"\"</a> <br/><br/><br/>";
        settingMap.put("from",settings.getEmailId());
        settingMap.put("link",link);
        return settingMap;
    }

}
