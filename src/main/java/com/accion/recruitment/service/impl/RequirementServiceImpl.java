package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.ClientServiceDAO;
import com.accion.recruitment.dao.RequirementServiceDAO;
import com.accion.recruitment.jpa.entities.ClientDetails;
import com.accion.recruitment.jpa.entities.Positions;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by MoinGodil on 1/11/17.
 */

@Service("requirementService")
@Transactional
public class RequirementServiceImpl implements RequirementService {


    @Autowired
    @Qualifier(value = "requirementServiceDAOImpl")
    private RequirementServiceDAO requirementServiceDAO;

    @Override
    public Boolean saveRequirements(final Positions requirement) throws SQLException{
        Boolean bolValue=this.requirementServiceDAO.saveRequirements(requirement);
        return bolValue;
    }

    @Override
    public List<Positions> findAllRequirement() throws SQLException{
        List<Positions> requirementList=this.requirementServiceDAO.findAllRequirement();
        return  requirementList;
    }



}
