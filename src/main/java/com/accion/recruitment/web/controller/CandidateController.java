package com.accion.recruitment.web.controller;

import com.accion.recruitment.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by MoinGodil on 1/20/17.
 */

@Controller
public class CandidateController {

    @Autowired
    CandidateService candidateService;
}
