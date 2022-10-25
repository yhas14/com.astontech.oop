package com.astontech.bo;

public class Address  extends BaseBO{
    private int AddressId;
    private int ClientId;
    private int PersonId;
    private int EntityTypeId;
    private int AddressNumber;

    //region CONSTRUCTOR
        public Address(){}

        public Address(int addressId, int clientId){
            this.setAddressId(addressId);
            this.setClientId(clientId);
        }
    //endregion

    //region GETTERS/SETTER
    public int getAddressId() {
        return AddressId;
    }

    public void setAddressId(int addressId) {
        AddressId = addressId;
    }

    public int getClientId() {
        return ClientId;
    }

    public void setClientId(int clientId) {
        ClientId = clientId;
    }

    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(int personId) {
        PersonId = personId;
    }

    public int getAddressNumber() {
        return AddressNumber;
    }

    public void setAddressNumber(int addressNumber) {
        AddressNumber = addressNumber;
    }
    //endregion

}
