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



}
