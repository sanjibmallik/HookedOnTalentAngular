package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.User;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/26/16 00:11 AM#$
 */
public interface LoginServiceDAO {

    public User getLoginUserByUserNameOREmailIdAndPassword(final String userNameOREmailId,final String password);
}
