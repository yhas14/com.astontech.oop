package com.astontech.bo;

public class LoyaltyAccount extends BaseBO{
    private int LoyaltyAccountId;
    private int EmployeeId;
    private int LoyaltyCompanyId;
    private String MemberNumber;

    //region CONSTRUCTOR
        public LoyaltyAccount(){}

        public LoyaltyAccount(int loyaltyAccountId, String memberNumber){
            this.setLoyaltyAccountId(loyaltyAccountId);
            this.setMemberNumber(memberNumber);
        }
    //endregion

    //region GETTERS/SETTERS
    public int getLoyaltyAccountId() {
        return LoyaltyAccountId;
    }

    public void setLoyaltyAccountId(int loyaltyAccount) {
        LoyaltyAccountId = loyaltyAccount;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public int getLoyaltyCompanyId() {
        return LoyaltyCompanyId;
    }

    public void setLoyaltyCompanyId(int loyaltyCompanyId) {
        LoyaltyCompanyId = loyaltyCompanyId;
    }

    public String getMemberNumber() {
        return MemberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        MemberNumber = memberNumber;
    }
    //endregion
}
