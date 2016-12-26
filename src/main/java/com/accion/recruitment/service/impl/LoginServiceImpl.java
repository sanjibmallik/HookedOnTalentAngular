package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.LoginServiceDAO;
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


    @Autowired
    private LoginServiceDAO loginServiceDAO;

    @Autowired
    public LoginServiceImpl(final LoginServiceDAO loginServiceDAO) {
        this.loginServiceDAO = loginServiceDAO;
    }

    @Override
    public User getLoginUserByUserNameOREmailIdAndPassword(final String userNameOREmailId,final String password) {

        User user=this.loginServiceDAO.getLoginUserByUserNameOREmailIdAndPassword(userNameOREmailId, password);
        return user;
    }

}
