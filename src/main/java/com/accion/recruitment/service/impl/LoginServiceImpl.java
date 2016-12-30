package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.LoginServiceDAO;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/23/16 00:11 AM#$
 */

@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {


    @Autowired
    @Qualifier(value = "loginServiceDAOImpl")
    private LoginServiceDAO loginServiceDAO;

    @Override
    public User getLoginUserByUserNameOREmailIdAndPassword(final String userNameOREmailId,final String password)throws SQLException{
        return (User)this.loginServiceDAO.getLoginUserByUserNameOREmailIdAndPassword(userNameOREmailId, password);
    }

}
