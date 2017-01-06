package com.accion.recruitment.service;

import com.accion.recruitment.jpa.entities.User;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 01/02/17 00:11 AM#$
 */
public interface UserEmailNotificationService {


    public  Boolean  sendUserCredentials(User user);

    public  Boolean  sendUserDisableStatus(User user);

    public  Boolean  sendUserEnableStatus(User user);

    public  Boolean  sendUserResetPassword(User user);

}
