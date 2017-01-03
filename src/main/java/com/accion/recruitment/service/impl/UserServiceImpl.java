package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.GroupServiceDAO;
import com.accion.recruitment.dao.LoginServiceDAO;
import com.accion.recruitment.dao.UserServiceDAO;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/26/16 00:11 AM#$
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    @Qualifier(value = "userServiceDAOImpl")
    private UserServiceDAO userServiceDAO;

    @Autowired
    @Qualifier(value = "groupServiceDAOImpl")
    private GroupServiceDAO groupServiceDAO;


    @Override
    public Boolean saveUser(final User user) throws SQLException{
        Boolean bolValue=this.groupServiceDAO.saveUserGroups(user);
        return bolValue;
    }

    @Override
    public List<User> findAllUser() throws SQLException{
        List<User> userList=this.userServiceDAO.findAllUser();
        return  userList;
    }

    @Override
    public User findUserByPropertyName(final String propName,final Object propValue)throws SQLException{
        return (User) this.userServiceDAO.findUserByPropertyName(propName, propValue);
    }

    @Override
    public User findUserById(final int userId )throws SQLException{
        return (User) this.userServiceDAO.findUserById(userId);
    }


}
