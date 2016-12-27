package com.accion.recruitment.web.controller;

import com.accion.recruitment.jpa.entities.Groups;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.LoginService;
import com.accion.recruitment.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/26/16 00:11 AM#$
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private final Md5PasswordEncoder encoder = new Md5PasswordEncoder();

    private final Random random = new SecureRandom();

    private final String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

    private final Integer PASSWORD_LENGTH = 6;

    private String password;

    private final Date currentDate = new Date();
    private final String dateFormat = "yyyy/MM/dd hh:mm:ss";
    private final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);


    @RequestMapping(value = "hot/createUser", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public Boolean createUser(final @RequestParam(required = false, value = "userImage") MultipartFile file,
                           final @ModelAttribute("user") User user,Principal principal) {


        if (user != null && file != null && !file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                user.setUserImage(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i=0; i<PASSWORD_LENGTH; i++)
        {
            int index = (int)(this.random.nextDouble()*letters.length());
            password += letters.substring(index, index+1);
        }

        user.setPassword(this.encoder.encodePassword(password, null));
        user.setCreatedBy(principal.getName());
        user.setCreatedDate(new Date(sdf.format(currentDate)));
        user.setUpdatedBy(principal.getName());
        user.setUpdatedDate(new Date(sdf.format(currentDate)));
        Boolean bolValue=this.userService.saveUser(user);

        return bolValue;
    }


    @RequestMapping(value = "hot/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        List<User> userList=this.userService.getAllUser();
        return  userList;
    }
}
