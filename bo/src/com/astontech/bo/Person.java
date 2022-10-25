package com.astontech.bo;

import common.helpers.StringHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person extends BaseBO implements Comparable, Serializable {
    //region PROPERTIES
    // PersonId
     private int PersonId;
    // Title
    private String Title;
    // FirstName
    private transient String FirstName;
    // LastName
    private String LastName;
    // CreateDate
    private Date CreateDate;
    // DisplayFirstName
    private String DisplayFirstName;
    // IsDeleted
    private int IsDeleted;
    //Gender
    private String Gender;

    // List of Email objects

    private List<Email>Emails;
    //endregion
    private static final long serialVersionUID = 54622233600l;

    //region CONSTRUCTOR
        public Person(){
            this.setEmails(new ArrayList());
        }

        public Person(String firstName){
           this.setFirstName(firstName);
        }

        public Person(int personId){
            this.setPersonId(personId);
        }
    //endregion

    //region CompareTo
    @Override
    public int compareTo(Object o) {
        Person other = (Person) o;
        if(getPersonId() > other.getPersonId()){
            return 1;
        }else if(getPersonId() < other.getPersonId()){
            return - 1;
        }else
            return 0;
    }
    //endregion

    //region GETTERS/SETTERS
    public void setPersonId(int personId){
        this.PersonId = personId;
    }
    public int getPersonId(){
        return this.PersonId;
    }

    public void setTitle(String title){
        this.Title = title;
    }
    public String getTitle(){
        return this.Title;
    }

    public void setFirstName(String firstName){
        this.FirstName = firstName;
    }
    public String getFirstName(){
        return this.FirstName;
    }

    public void setLastName(String lastName){
        this.LastName = lastName;
    }
    public String getLastName(){
        return this.LastName;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public void setDisplayFirstName(String displayFirstName){
        this.DisplayFirstName = displayFirstName;
    }
    public String getDisplayFirstName(){
        return this.DisplayFirstName;
    }

    public int getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        IsDeleted = isDeleted;
    }

    public void setGender(String gender){
        this.Gender = gender;
    }
    public String getGender(){
        return this.Gender;
    }

    public List<Email> getEmails() {
        return this.Emails;
    }
    public void setEmails(List<Email> emailList) {
        this.Emails = emailList;
    }
    //endregion

    //region CUSTOM METHODS

    public String toString(){
            return StringHelper.toString(this.PersonId);
    }

    public String GetFullName(){
        if(StringHelper.isNullorEmpty(this.FirstName) && StringHelper.isNullorEmpty(this.LastName))
            return "NO Name Set";
        else {
            if(StringHelper.isNullorEmpty(this.FirstName))
                return this.LastName;
            else if(StringHelper.isNullorEmpty(this.LastName))
                return this.FirstName;
            else
                return this.FirstName + " "+ this.LastName;
        }

    }

    public String ToString(){
        return "PersonId = " + this.PersonId + " FullName = " + this.GetFullName() + " FirstName = " + this.FirstName;
    }

    //endregion



}

