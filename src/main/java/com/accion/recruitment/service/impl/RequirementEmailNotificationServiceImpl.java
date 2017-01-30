package com.accion.recruitment.service.impl;

import com.accion.recruitment.common.constants.EmailNotificationConstants;
import com.accion.recruitment.common.helper.EmailNotificationHelper;
import com.accion.recruitment.common.helper.SettingsHelper;
import com.accion.recruitment.jpa.entities.Positions;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.RequirementEmailNotificationService;
import com.accion.recruitment.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 30/01/17 00:11 AM#$
 */

@Service("requirementEmailNotificationService")
public class RequirementEmailNotificationServiceImpl implements RequirementEmailNotificationService{


    @Autowired
    private EmailNotificationHelper emailNotificationHelper;

    @Autowired
    private SettingsService settingsService;

    private SettingsHelper settingsHelper;

    public  Boolean  sendRequirementOpenStatus(Positions requirements,List<String> toUser){
        String subject = "Requirement: "+requirements.getJobTitle()+" is re-opened";
        String body = "<html><head>";
        body += "<style>table {border-collapse: collapse;} table, td, th {border: 2px solid black;}</style></head>";
        body += "Hello Team, ";
        body += "<br/><br/>";
        body += "This is a system generated mail to notify the reopening of  Requirement :"+requirements.getJobTitle()+".<br/><br/>";
        body += "You can resume corresponding regular activity for the requirement.<br/><br/>";
        body += "For more details you can contact your Account Manager or Admin. <br/><br/>";
        body += "Please login to the application using your user id & password using the application link provided below.<br/></br>";
        body+=this.settingsHelper.getSettingsMap().get("link");
        body+= EmailNotificationConstants.SIGNATURE;
        return this.emailNotificationHelper.sendMail(toUser,this.settingsHelper.getSettingsMap().get("from"),subject,body);

    }

    public  Boolean  sendRequirementCloseStatus(Positions requirements,List<String> toUser){
        String subject = "Requirement: "+requirements.getJobTitle()+" is closed";
        String body = "<html><head>";
        body += "Hello Team";
        body += "<br/><br/>";
        body += "This is a system generated mail to notify the closure of Requirement : "+requirements.getJobTitle()+".<br/><br/>" +
                " There would not be any more activity for this requirement.<br/><br/>";
        body += "For more details you can contact your Account Manager or Admin.<br/><br/>";
        body += "Please login to the application using your user id & password using the application link provided below.<br/></br>";
        body+=this.settingsHelper.getSettingsMap().get("link");
        body+= EmailNotificationConstants.SIGNATURE;
        return this.emailNotificationHelper.sendMail(toUser,this.settingsHelper.getSettingsMap().get("from"),subject,body);

    }


}
