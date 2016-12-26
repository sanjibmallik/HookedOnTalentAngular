package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.User;



/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * @version $Revision: 0001 $, $Date:: 12/23/16 00:11 AM#$
 */
public interface UserServiceDAO {

    public User getUserByUserNameOREmailId(final String userNameOREmailId);

    public User getUserByUserNameOREmailIdAndPassword(final String userNameOREmailId,final String password);

    public User getUserByUserNameOREmailIdAndPasswordIsDisabled(final String userNameOREmailId,final String password);


}
