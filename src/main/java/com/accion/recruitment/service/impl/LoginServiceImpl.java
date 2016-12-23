package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.TechnicalScreenerDAO;
import com.accion.recruitment.dao.UserServiceDAO;
import com.accion.recruitment.jpa.entities.TechnicalScreener;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/23/16 00:11 AM#$
 */
@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {


    private UserServiceDAO userServiceDAO;

    @Autowired
    public LoginServiceImpl(final UserServiceDAO userServiceDAO) {
        this.userServiceDAO = userServiceDAO;
    }

    @Override
    public User getLoginUser(final String userName,final String password) {

        User user=this.userServiceDAO.getLoginUserByUserNameAndPassword(userName, password);
        return user;
    }

}
