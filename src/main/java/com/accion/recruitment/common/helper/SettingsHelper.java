package com.accion.recruitment.common.helper;

import com.accion.recruitment.common.constants.EmailNotificationConstants;
import com.accion.recruitment.jpa.entities.Settings;
import com.accion.recruitment.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 30/01/17 00:11 AM#$
 */

@Service("settingHelperService")
public class SettingsHelper {


    @Autowired
    private SettingsService settingsService;

    public HashMap<String,String> getSettingsMap(){
        Settings settings=this.settingsService.findSettingsDetailsById(1);
        HashMap<String,String> settingMap=new HashMap<String, String>();
        String link= "Link: <a href=\""+settings.getDomainName()+""+ EmailNotificationConstants.CHANGE_PASSWORD_URL+"\">"+settings.getDomainName()+""+EmailNotificationConstants.CHANGE_PASSWORD_URL+"\"</a> <br/><br/><br/>";
        String INTERVIEW_VIDEO_GUIDE= " <a href=\""+settings.getDomainName()+""+ EmailNotificationConstants.INTERVIEW_URL+"\">"+settings.getDomainName()+""+EmailNotificationConstants.INTERVIEW_URL+"\"</a> <br/><br/><br/>";


        settingMap.put("from",settings.getEmailId());
        settingMap.put("domain",settings.getDomainName());
        settingMap.put("link",link);
        settingMap.put("INTERVIEW_VIDEO_GUIDE",INTERVIEW_VIDEO_GUIDE);

        return settingMap;
    }

}
