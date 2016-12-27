package com.accion.recruitment.web.controller;

import com.accion.recruitment.service.DashBoardService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/27/16 00:11 AM#$
 */

@Controller
public class DashBoardController {

    @Autowired
    private DashBoardService dashBoardService;

    @RequestMapping(value = "hot/getDashBoardCounts", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public String getDashBoardCounts() {

        JSONObject jsonObject=new JSONObject();
        final Long userCounts=this.dashBoardService.getUserCounts();
        try {
            jsonObject.put("totalUsers",userCounts);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonObject.toString();
    }
}
