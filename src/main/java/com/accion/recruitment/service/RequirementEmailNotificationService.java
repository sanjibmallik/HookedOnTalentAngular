package com.accion.recruitment.service;

import com.accion.recruitment.jpa.entities.Positions;
import com.accion.recruitment.jpa.entities.User;

import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 30/01/17 00:11 AM#$
 */

public interface RequirementEmailNotificationService {

    public  Boolean  sendRequirementOpenStatus(Positions requirements,List<String> toUser);

    public  Boolean  sendRequirementCloseStatus(Positions requirements,List<String> toUser);

    public  Boolean  sendRequirementMailToTs(Positions requirements,User toUser);
}
