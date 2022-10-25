package com.astontech.bo;

import java.util.regex.Pattern;

public class Email extends BaseBO{
    //region PROPERTIES
    private int EmailId;
    private int EmployeeId;
    private String EmailAddress;
    private int EntityType;
    private EntityType EmailType;

    private int EntityTypeId;

    //endregion

    //region CONSTRUCTORS
    public Email(){
        this.EmailType = new EntityType();
    }

    public Email(String emailAddress){
        this.EmailType = new EntityType();
        this.EmailAddress = emailAddress;
    }
    //endregion

    //region GETTERS/SETTERS
    public int getEmailId() {
        return EmailId;
    }

    public void setEmailId(int emailId) {
        EmailId = emailId;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public EntityType getEmailType() {
        return EmailType;
    }

    public void setEmailType(EntityType emailType) {
        EmailType = emailType;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public int getEntityTypeId() {
        return EntityTypeId;
    }

    public void setEntityTypeId(int entityTypeId) {
        EntityTypeId = entityTypeId;
    }

    //endregion

    //region CUSTOM METHOD 2 for LAB
    public static boolean patternMatches(String emailAddress){
        return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                .matcher(emailAddress)
                .matches();
    }
    public  String checkEmail(){
        if(patternMatches(this.EmailAddress)){
            return "You used a valid Email, here is your Email: " + this.EmailAddress;
        }else {
            return "Please Provide a valid Email";
        }
    }



    //endregion
}
