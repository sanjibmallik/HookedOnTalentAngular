package com.accion.recruitment.dao.impl;


import com.accion.recruitment.common.constants.LoginConstants;
import com.accion.recruitment.dao.LoginServiceDAO;
import com.accion.recruitment.dao.UserServiceDAO;
import com.accion.recruitment.jpa.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/26/16 00:11 AM#$
 */

@Repository(value = "loginServiceDAOImpl")
public class LoginServiceDAOImpl implements LoginServiceDAO {

    @Autowired
    @Qualifier(value = "userServiceDAOImpl")
    private UserServiceDAO userServiceDAO;


    @Override
    public User getLoginUserByUserNameOREmailIdAndPassword(final String userNameOREmailId,final String password){

        User loginUserObject=new User();

        /*User checkUserNameOREmailId=this.userServiceDAO.findUserByUserNameOREmailId(userNameOREmailId);
        if(checkUserNameOREmailId==null){
            loginUserObject.setErrorMessage(LoginConstants.WRONG_CREDENTIALS);
            return loginUserObject;
        }*/

        User checkUserNameOREmailIdAndPassword=this.userServiceDAO.findUserByUserNameOREmailIdAndPassword(userNameOREmailId, password);
        if(checkUserNameOREmailIdAndPassword==null){
            loginUserObject.setErrorMessage(LoginConstants.WRONG_CREDENTIALS);
            return loginUserObject;
        }

        User checkUserNameOREmailIdAndPasswordAndEnabled=this.userServiceDAO.findUserByUserNameOREmailIdAndPasswordAndDisabledOREnabled(userNameOREmailId, password, true);
        if(checkUserNameOREmailIdAndPasswordAndEnabled==null){
            loginUserObject.setErrorMessage(LoginConstants.USER_DISABLED);
            return loginUserObject;
        }else{
            loginUserObject=(User)checkUserNameOREmailIdAndPasswordAndEnabled;
        }

        return loginUserObject;
    }

}
