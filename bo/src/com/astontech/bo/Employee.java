package com.astontech.bo;

import java.util.Date;

public class Employee extends Person{
    // Parameters we use in getters/setters
    private int EmployeeId;
    private Date HireDate;
    private Date TermDate;
    private Date BirthDate;
    private String FirstName;
    private EmployeeProject Project;

    //Constructors

    public Employee(){}
    public Employee(EmployeeProject project, int employeeId){
        this.Project = new EmployeeProject();
        this.setProject(project);
    }
    public Employee(String firstName, String lastName){
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    public Employee(int employeeId, String firstName){
        this.setEmployeeId(employeeId);
        this.setFirstName(firstName);
    }
    public Employee(String lastName){
        this.setLastName(lastName);
    }

    // GETTERS/SETTERS
    public void setEmployeeId(int employeeId) {
        this.EmployeeId = employeeId;
    }
    public int getEmployeeId(){
        return this.EmployeeId;
    }

    public void setHireDate(Date hireDate){
        this.HireDate = hireDate;
    }

    public Date getHireDate(){
        return this.HireDate;
    }

    public void setTermDate(Date termDate){
        this.TermDate = termDate;
    }

    public Date getTermDate(){
        return this.TermDate;
    }

    public EmployeeProject getProject() {
        return Project;
    }

    public void setProject(EmployeeProject project) {
        Project = project;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }
}

