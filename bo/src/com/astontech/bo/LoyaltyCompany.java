package com.astontech.bo;

public class LoyaltyCompany extends BaseBO{
    private int LoyaltyCompanyId;
    private String CompanyName;
    private int EntityTypeId;

    //region CONSTRUCTOR
        public LoyaltyCompany(){}
        public  LoyaltyCompany(int loyaltyCompanyId, String companyName){
            this.setLoyaltyCompanyId(loyaltyCompanyId);
            this.setCompanyName(companyName);
        }
    //endregion

    //region GETTERS/SETTERS
    public int getLoyaltyCompanyId() {
        return LoyaltyCompanyId;
    }

    public void setLoyaltyCompanyId(int loyaltyCompanyId) {
        LoyaltyCompanyId = loyaltyCompanyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }
    //endregion

    //region CUSTOM METHOD
        public String greeting(){
            return "Hello from " + CompanyName;
        }
    //endregion
}
