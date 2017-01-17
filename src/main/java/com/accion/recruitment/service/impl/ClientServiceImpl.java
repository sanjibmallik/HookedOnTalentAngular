package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.ClientServiceDAO;
import com.accion.recruitment.jpa.entities.*;
import com.accion.recruitment.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 10/01/17 00:11 AM#$
 */

@Service("clientService")
@Transactional

public class ClientServiceImpl implements ClientService {

    @Autowired
    @Qualifier(value = "clientServiceDAOImpl")
    private ClientServiceDAO clientServiceDAO;


    @Override
    public ClientDetails findClientDetailsByPropertyName(final String propName,final Object propValue)throws SQLException {
        return (ClientDetails) this.clientServiceDAO.findClientDetailsByPropertyName(propName, propValue);
    }


    @Override
    public Boolean saveClientDetails(final ClientDetails clientDetails) throws SQLException{
        Boolean bolValue=this.clientServiceDAO.saveClientDetails(clientDetails);
        return bolValue;
    }


    @Override
    public Boolean saveClientContacts(final ClientContacts clientContacts) throws SQLException{
        Boolean bolValue=this.clientServiceDAO.saveClientContacts(clientContacts);
        return bolValue;
    }

    @Override
    public Boolean saveIndustry(final Industry industry) throws SQLException{
        Boolean bolValue=this.clientServiceDAO.saveObject(industry);
        return bolValue;
    }

    @Override
    public Boolean saveEngagementModel(final EngagementModel engagementModel) throws SQLException{
        Boolean bolValue=this.clientServiceDAO.saveObject(engagementModel);
        return bolValue;
    }

    @Override
    public List<EngagementModel> findAllEngagementModel() throws SQLException{
        return this.clientServiceDAO.findAllEngagementModel();

    }

    @Override
    public List<Industry> findAllIndustry() throws SQLException{
        return this.clientServiceDAO.findAllIndustry();

    }
}
