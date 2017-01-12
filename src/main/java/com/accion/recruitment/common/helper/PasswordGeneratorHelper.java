package com.accion.recruitment.common.helper;

import com.accion.recruitment.common.constants.UserConstants;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/01/17 00:11 AM#$
 */
public class PasswordGeneratorHelper {

    private final Random random = new SecureRandom();

    public final String generatePassword(){
        String password="";
        try{
            for (int i=0; i< UserConstants.PASSWORD_LENGTH; i++)
            {
                int index = (int)(this.random.nextDouble()* UserConstants.LETTERS.length());
                password += UserConstants.LETTERS.substring(index, index+1);
            }
        }catch (Exception e){
            password= UserConstants.DEFAULT_PASSWORD;
        }
        return password;

    }
}
