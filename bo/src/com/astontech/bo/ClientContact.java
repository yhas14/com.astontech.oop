package com.astontech.bo;

public class ClientContact extends BaseBO {
    private int ClientContactId;
    private int ClientId;
    private int EntityTypeId;
    private int PersonId;

    //region CONSTRUCTOR
        public ClientContact(){}

        public ClientContact(int clientContactId, int clientId){
            this.setClientContactId(clientContactId);
            this.setClientId(clientId);
        }
    //endregion

    //region GETTERS/SETTERS
    public int getClientContactId() {
        return ClientContactId;
    }

    public void setClientContactId(int clientContactId) {
        ClientContactId = clientContactId;
    }

    public int getClientId() {
        return ClientId;
    }

    public void setClientId(int clientId) {
        ClientId = clientId;
    }

    public int getEntityTypeId() {
        return EntityTypeId;
    }

    public void setEntityTypeId(int entityTypeId) {
        EntityTypeId = entityTypeId;
    }

    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(int personId) {
        PersonId = personId;
    }
    //endregion
}
