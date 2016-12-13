package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.TechnicalScreenerDAO;
import com.accion.recruitment.dao.impl.TechnicalScreenerDAOImpl;
import com.accion.recruitment.jpa.entities.Admin;
import com.accion.recruitment.jpa.entities.TechnicalScreener;
import com.accion.recruitment.service.TechnicalScreenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AL1451 on 11/30/16.
 */
@Service("technicalScreenerService")
@Transactional
public class TechnicalScreenerServiceImpl implements TechnicalScreenerService {

    private TechnicalScreenerDAO technicalScreenerDAO;

    @Autowired
    public TechnicalScreenerServiceImpl(final TechnicalScreenerDAO technicalScreenerDAO) {
        this.technicalScreenerDAO = technicalScreenerDAO;
    }

    @Override
    public List<TechnicalScreener> findAll() {
        String query="";
        this.technicalScreenerDAO.getTSByQuery(query);
        return null;
    }
}
