package com.accion.recruitment.service;

import com.accion.recruitment.jpa.entities.TechnicalScreener;
import com.accion.recruitment.jpa.entities.User;

import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/23/16 00:11 AM#$
 */
public interface LoginService {

    public User getLoginUserByUserNameOREmailIdAndPassword(final String UserNameOREmailId,final String password);
}
