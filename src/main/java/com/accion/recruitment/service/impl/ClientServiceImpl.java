package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.ClientServiceDAO;
import com.accion.recruitment.jpa.entities.ClientDetails;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

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

}
