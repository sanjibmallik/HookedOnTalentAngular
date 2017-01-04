package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.SettingServiceDAO;
import com.accion.recruitment.jpa.entities.Settings;
import com.accion.recruitment.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 01/02/17 00:11 AM#$
 */

@Service("settingsService")
@Transactional
public class SettingsServiceImpl implements SettingsService {


    @Autowired
    @Qualifier(value = "settingServiceDAOImpl")
    private SettingServiceDAO settingServiceDAO;


    @Override
    public Boolean saveSettings(Settings settings) {
        return this.settingServiceDAO.saveSettings(settings);
    }

    @Override
    public Settings getSettingsDetailsById(final int settingId) {
        return (Settings) this.settingServiceDAO.findSettingsDetailsById(settingId);
    }

}
