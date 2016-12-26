package com.accion.recruitment.dao.impl;


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

    private final String userNameOREmailIdErrorMsg="UserName/EmailId Is Wrong";
    private final String userNameOREmailIdAndPasswordErrorMsg="UserName/Password Is Wrong";
    private final String userDisabledErrorMsg="User Is Disabled";

    @Override
    public User getLoginUserByUserNameOREmailIdAndPassword(final String userNameOREmailId,final String password){

        User loginUserObject=new User();

        User checkUserNameOREmailId=this.userServiceDAO.getUserByUserNameOREmailId(userNameOREmailId);
        if(checkUserNameOREmailId==null){
            loginUserObject.setErrorMessage(userNameOREmailIdErrorMsg);
            return loginUserObject;
        }

        User checkUserNameOREmailIdAndPassword=this.userServiceDAO.getUserByUserNameOREmailIdAndPassword(userNameOREmailId, password);
        if(checkUserNameOREmailIdAndPassword==null){
            loginUserObject.setErrorMessage(userNameOREmailIdAndPasswordErrorMsg);
            return loginUserObject;
        }

        User checkUserNameOREmailIdAndPasswordAndEnabled=this.userServiceDAO.getUserByUserNameOREmailIdAndPasswordAndDisabledOREnabled(userNameOREmailId, password, true);
        if(checkUserNameOREmailIdAndPasswordAndEnabled==null){
            loginUserObject.setErrorMessage(userDisabledErrorMsg);
            return loginUserObject;
        }else{
            loginUserObject=(User)checkUserNameOREmailIdAndPasswordAndEnabled;
        }

        return loginUserObject;
    }

}
