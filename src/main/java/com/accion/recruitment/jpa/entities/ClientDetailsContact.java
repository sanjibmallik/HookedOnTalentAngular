package com.accion.recruitment.jpa.entities;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 11/01/17 00:11 AM#$
 */

public class ClientDetailsContact {

    private ClientDetails clientDetails;
    private ClientContacts clientContacts;

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
}
