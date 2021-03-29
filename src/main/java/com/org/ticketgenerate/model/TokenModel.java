package com.org.ticketgenerate.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TokenModel {

    @Id
    private  int Tenantid;

    private int Clientid;

    private String Clientsecret ;

    private String Resource;

    public String getResource() {
        return Resource;
    }

    public void setResource(String resource) {
        Resource = resource;
    }

    public int getTenantid() {
        return Tenantid;
    }

    public void setTenantid(int tenantid) {
        Tenantid = tenantid;
    }

    public int getClientid() {
        return Clientid;
    }

    public void setClientid(int clientid) {
        Clientid = clientid;
    }

    public String getClientsecret() {
        return Clientsecret;
    }

    public void setClientsecret(String clientsecret) {
        Clientsecret = clientsecret;
    }
}
