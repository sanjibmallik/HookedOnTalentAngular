package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.UserServiceDAO;
import com.accion.recruitment.service.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/27/16 00:11 AM#$
 */
@Service("dashBoardService")
@Transactional
public class DashBoardServiceImpl implements DashBoardService {

    @Autowired
    @Qualifier(value = "userServiceDAOImpl")
    private UserServiceDAO userServiceDAO;

    public Long getUserCounts(){
        final Long size=this.userServiceDAO.size();
        return  size;
    }


}
