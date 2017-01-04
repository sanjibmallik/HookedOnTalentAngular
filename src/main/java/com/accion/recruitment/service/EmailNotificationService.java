package com.accion.recruitment.service;

import com.accion.recruitment.jpa.entities.User;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 01/02/17 00:11 AM#$
 */
public interface EmailNotificationService {


    public  Boolean  sendUserCredentials(User user);

}
