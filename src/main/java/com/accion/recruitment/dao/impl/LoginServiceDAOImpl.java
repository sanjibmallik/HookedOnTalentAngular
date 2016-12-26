package com.accion.recruitment.dao.impl;


import com.accion.recruitment.dao.LoginServiceDAO;
import com.accion.recruitment.dao.UserServiceDAO;
import com.accion.recruitment.jpa.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/26/16 00:11 AM#$
 */

@Repository
public class LoginServiceDAOImpl implements LoginServiceDAO {

    @Autowired
    private UserServiceDAO userServiceDAO;

    @Override
    public User getLoginUserByUserNameOREmailIdAndPassword(final String userNameOREmailId,final String password){

        User userObject=new User();

        User checkUserNameOREmailId=this.userServiceDAO.getUserByUserNameOREmailId(userNameOREmailId);
        if(checkUserNameOREmailId==null){
            userObject.setErrorMessage("userName/emailId is wrong");
            return userObject;
        }

        User checkUserNameOrEmailId=this.userServiceDAO.getUserByUserNameOREmailIdAndPassword(userNameOREmailId, password);
        if(checkUserNameOrEmailId==null){
            userObject.setErrorMessage("userName/password is wrong");
            return userObject;
        }

        User checkUserIsDisabled=this.userServiceDAO.getUserByUserNameOREmailIdAndPasswordIsDisabled(userNameOREmailId, password);
        if(checkUserIsDisabled==null){
            userObject.setErrorMessage("user is disabled");
            return userObject;
        }else{
            userObject=(User)checkUserIsDisabled;
        }

        return userObject;
    }

}
