package com.accion.recruitment.service.impl;

import com.accion.recruitment.common.helper.EmailNotificationHelper;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 01/02/17 00:11 AM#$
 */
@Service("emailNotificationService")
public class EmailNotificationServiceImpl implements EmailNotificationService {

    @Autowired
    private EmailNotificationHelper emailNotificationHelper;

    public String subject;
    public String body;

    public  Boolean  sendUserCredentials(User user){

        subject = "HookedOn Talent Login Credentials";

        body += "<style>table {border-collapse: collapse;} table, td, th {border: 2px solid black;}</style></head>";
        body += "Hello "+user.getFirstName()+"<br/>";
        body += "Congratulation!! <br/>";
        body += "You are now a part of HookedOn Talent App <br/>";
        body += "Your User Id and Password has been created and the credentials are as follows <br/><br/>";
        body += "UserName : "+user.getUserName();
        body += "<br/>";
        body += "Password :"+user.getPassword();
        body += "<br/></br>";
        body += "Please login to the application and change the password using the application link provided below.";

        return this.emailNotificationHelper.sendMail(user.getEmailId(),subject,body);

    }
}
