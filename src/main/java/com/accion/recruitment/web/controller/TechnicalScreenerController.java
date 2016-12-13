package com.accion.recruitment.web.controller;

/**
 * Created by AL1451 on 11/30/16.
 */

import com.accion.recruitment.jpa.entities.TechnicalScreener;
import com.accion.recruitment.service.TechnicalScreenerService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TechnicalScreenerController {

    @Autowired
    private TechnicalScreenerService technicalScreenerService;

    @ApiOperation(value = "Get all Technical Screener")
    @RequestMapping(value = "api/technicalScreener", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<TechnicalScreener> list() {
        System.out.println("hitting");
        return technicalScreenerService.findAll();
    }


}
