package com.astontech.bo;

import java.util.Date;

public class Client extends BaseBO{
    private int ClientId;
    private String ClientName;
    private Date CreateDate;
    private ClientContact Clientcontact;
    //region CONSTRUCTOR
        public Client(){}

        public Client(int clientId,String clientName){
            this.setClientId(clientId);
            this.setClientName(clientName);
        }

        public Client(ClientContact clientContact, int clientId){
            this.Clientcontact = new ClientContact();
            this.setClientId(clientId);
        }
    //endregion
    //region GETTERS/SETTERS
    public int getClientId() {
        return ClientId;
    }

    public void setClientId(int clientId) {
        ClientId = clientId;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public ClientContact getClientcontact() {
        return Clientcontact;
    }

    public void setClientcontact(ClientContact clientcontact) {
        Clientcontact = clientcontact;
    }
    //endregion

    //region CUSTOM METHODS
    public static boolean isNullorEmpty(String a){
            return a != null || a.length() != 0;
    }
    public String ClientName(){
            if(isNullorEmpty(this.ClientName)){
                return "Hello this client is: " + this.ClientName;
            }else {
                return "NO ClientName Set";
            }
    }



    //endregion
}
