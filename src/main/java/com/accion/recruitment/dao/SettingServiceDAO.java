package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.Settings;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 01/02/17 00:11 AM#$
 */
public interface SettingServiceDAO<E> {

    public Boolean saveSettings(Settings settings);

    public Settings findSettingsDetailsById(final int settingId);
}
