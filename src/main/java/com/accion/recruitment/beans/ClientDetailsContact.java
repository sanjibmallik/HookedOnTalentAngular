package com.accion.recruitment.beans;

import com.accion.recruitment.jpa.entities.ClientContacts;
import com.accion.recruitment.jpa.entities.ClientDetails;
import com.accion.recruitment.jpa.entities.User;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 11/01/17 00:11 AM#$
 */

public class ClientDetailsContact {

    private ClientDetails clientDetails;
    private ClientContacts clientContacts;
    private User user;

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    public ClientContacts getClientContacts() {
        return clientContacts;
    }

    public void setClientContacts(ClientContacts clientContacts) {
        this.clientContacts = clientContacts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
