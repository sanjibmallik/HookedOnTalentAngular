package com.accion.recruitment.service;

import com.accion.recruitment.jpa.entities.TechnicalScreener;
import com.accion.recruitment.jpa.entities.User;

import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * @version $Revision: 0001 $, $Date:: 12/23/16 00:11 AM#$
 */
public interface LoginService {

    public User getLoginUser(final String UserName,final String password);
}
